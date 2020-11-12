package com.itmuch.msclass.service;

import com.itmuch.msclass.domain.dto.UserDTO;
import com.itmuch.msclass.domain.dto.UserMoneyDTO;
import com.itmuch.msclass.domain.entity.Lesson;
import com.itmuch.msclass.domain.entity.LessonUser;
import com.itmuch.msclass.repository.LessonRepository;
import com.itmuch.msclass.repository.LessonUserRepository;
import com.itmuch.msclass.feign.MsUserFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * @program: ms-user
 * @description:
 * @author: Long Ao Tian
 * @create: 2020-09-15 21:18
 **/
@Transactional(rollbackFor = Exception.class)
@Service
public class LessonService {
    private static final Logger LOGGER= LoggerFactory.getLogger(LessonService.class);
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LessonRepository lessonRepository;
    @Autowired
    private LessonUserRepository lessonUserRepository;
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private MsUserFeignClient msUserFeignClient;
    @Autowired
    private Source source;
    @Transactional(rollbackFor = Exception.class)
    public Lesson buyById(Integer id, HttpServletRequest request){
        // 1. 根据 id 查询 lesson
        Lesson lesson=this.lessonRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("该课程不存在"));

        // 2. 根据lesson.id 查询 user_lesson
        LessonUser lessonUser = this.lessonUserRepository.findByLessonId(id);
        if(lessonUser!=null){
            return lesson;
        }
        // 3. 如果 user_lesson== null && 用户余额 > lesson.price
        Integer userId= ((Integer) request.getAttribute("userId"));
                                                    //request.getHeader("Authorization") 或者 buyById 方法定义参数处加上@RequestHeadler("Authorization") String token
        UserDTO userDTO= this.msUserFeignClient.findUserById(userId);
        BigDecimal money = userDTO.getMoney().subtract(lesson.getPrice());
        if(money.doubleValue()<0){
            throw new IllegalArgumentException("余额不足");
        }
        // 1.发送消息给用户微服务,让他扣减金额
        this.source.output()
                .send(
                        MessageBuilder.withPayload(
                                new UserMoneyDTO(
                                        userId,
                                        lesson.getPrice(),
                                        "购买课程",
                                        String.format("%s购买了id为%s的课程",userId,id)
                                )
                        ).build()
                );
        // 2向lesson_user 表插入数据
        LessonUser lu = new LessonUser();
        lu.setLessonId(id);
        lu.setUserId(userId);
        this.lessonUserRepository.save(lu);
        return  lesson;
    }
}


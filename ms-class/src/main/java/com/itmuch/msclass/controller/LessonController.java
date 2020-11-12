package com.itmuch.msclass.controller;

import com.itmuch.msclass.auth.Login;
import com.itmuch.msclass.domain.entity.Lesson;
import com.itmuch.msclass.service.LessonService;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: ms-user
 * @description:
 * @author: Long Ao Tian
 * @create: 2020-09-15 21:22
 **/

@RequestMapping("/lessons")
@RestController
// 放在方法上更细粒度
//@RateLimiter(name ="lessonController" )
public class LessonController {
    public static final Logger LOGGER= LoggerFactory.getLogger(LessonController.class);
    @Autowired
    private LessonService lessonService;

    /**
     * 购买指定id的课程
     * 注意：
     * 基于线程池的BulkHead无法传递ThreadLocal
     * 如果应用使用了ThreadLocal，那么不要去使用基于线程池的BulkHead
     * @param id
     * @param request
     * @return
     */
    @Login
    @GetMapping("/buy/{id}")
    @RateLimiter(name ="buyById" ,fallbackMethod = "buyByIdFallback")
    public Lesson buyById(@PathVariable Integer id, HttpServletRequest request) {
        return this.lessonService.buyById(id,request);
    }

    public Lesson buyByIdFallback(@PathVariable Integer id,HttpServletRequest request,Throwable throwable){
        LOGGER.info("发生Fallbask",throwable);
        // 假设从本地缓存获取...
        return new Lesson();
    }

}


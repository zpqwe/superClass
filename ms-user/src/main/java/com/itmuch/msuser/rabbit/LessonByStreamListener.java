package com.itmuch.msuser.rabbit;

import com.itmuch.msuser.domain.dto.UserMoneyDTO;
import com.itmuch.msuser.domain.entity.User;
import com.itmuch.msuser.domain.entity.UserAccountEventLog;
import com.itmuch.msuser.repository.UserAccountEventLogRepository;
import com.itmuch.msuser.repository.UserRepository;
import com.itmuch.msuser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

/**
 * @program: ms-user
 * @description:
 * @author: Long Ao Tian
 * @create: 2020-11-04 16:32
 **/
@Component
public class LessonByStreamListener {
@Autowired
private UserService userService;
    @StreamListener(Sink.INPUT)
    public void lessonBy(UserMoneyDTO moneyDTO){
        this.userService.lessonBy(moneyDTO);
    }
}


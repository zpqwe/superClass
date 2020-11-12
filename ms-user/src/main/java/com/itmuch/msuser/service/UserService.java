package com.itmuch.msuser.service;

import com.itmuch.msuser.domain.dto.UserLoginDTO;
import com.itmuch.msuser.domain.dto.UserMoneyDTO;
import com.itmuch.msuser.domain.entity.User;
import com.itmuch.msuser.domain.entity.UserAccountEventLog;
import com.itmuch.msuser.jwt.JwtOperator;
import com.itmuch.msuser.repository.UserAccountEventLogRepository;
import com.itmuch.msuser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

/**
 * @program: ms-user
 * @description:
 * @author: Long Ao Tian
 * @create: 2020-09-15 21:18
 **/
@Transactional(rollbackFor = Exception.class)
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserAccountEventLogRepository userAccountEventLogRepository;

    @Autowired
    private JwtOperator jwtOperator;
    public User findById(Integer id){
        return this.userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("用户不存在"));
    }

    public void lessonBy(UserMoneyDTO moneyDTO){
        Integer userId = moneyDTO.getUserId();
        BigDecimal money = moneyDTO.getMoney();
        // 2.扣减余额
        Optional<User> optionalUser = this.userRepository.findById(userId);
        User user = optionalUser.orElseThrow(() -> new IllegalArgumentException("当前用户ID不存在"));
        user.setMoney(user.getMoney().subtract(money));
        this.userRepository.save(user);
        // 3.记录日志 user_account_event_log
        this.userAccountEventLogRepository.save(
                new UserAccountEventLog(
                        userId,
                        money,
                        moneyDTO.getEvent(),
                        moneyDTO.getDescription(),
                        new Date()
                )
        );

    }

    public String login(UserLoginDTO loginDTO) {
        //1.校验账号密码【直接使用明文 MD5/BCript/SHA1/】是否匹配
        //让springdata jpa 发送 select* from user where username=? and password=?
      return  this.userRepository.findByUsernameAndPassword(loginDTO.getUsername(),loginDTO.getPassword())
        .map(user -> {
            //2 .如果匹配则颁发token
            HashMap<String,Object> userInfo=new HashMap<>();
            userInfo.put("userId",user.getId());
            userInfo.put("userName",user.getUsername());
            userInfo.put("role","vip");
            return jwtOperator.generateToken(userInfo);
        }).orElseThrow(()->new IllegalArgumentException("账号密码不匹配"));
    }
}


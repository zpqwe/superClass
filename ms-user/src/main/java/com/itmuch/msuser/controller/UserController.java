package com.itmuch.msuser.controller;

import com.itmuch.msuser.auth.Login;
import com.itmuch.msuser.domain.dto.UserLoginDTO;
import com.itmuch.msuser.domain.entity.User;
import com.itmuch.msuser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: ms-user
 * @description:
 * @author: Long Ao Tian
 * @create: 2020-09-15 21:22
 **/
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 1.如果用户没有登录，返回HTTP 401错误码
     * 2.如果已经登录了，正常访问
     * @param id
     * @return
     */
    @Login
    @GetMapping("/users/{id}")
    public User findById(@PathVariable Integer id){
        return this.userService.findById(id);
    }

    @PostMapping("/login")
    /*@RequestBody 把http消息体转化成loginDTO对象*/
    public String login(@RequestBody UserLoginDTO loginDTO){
        return this.userService.login(loginDTO);
    }

}


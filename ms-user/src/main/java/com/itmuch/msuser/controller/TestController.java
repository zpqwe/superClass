package com.itmuch.msuser.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: ms-user
 * @description:
 * @author: Long Ao Tian
 * @create: 2020-09-23 16:39
 **/
@RefreshScope
@RestController
public class TestController {

    @Value("${first.config}")
    private String config;
    @GetMapping("/test-config")
    public String testConfig(){
        return this.config;
    }
}


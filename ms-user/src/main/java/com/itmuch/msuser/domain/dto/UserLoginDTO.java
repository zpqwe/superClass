package com.itmuch.msuser.domain.dto;

/**
 * @program: ms-user
 * @description:
 * @author: Long Ao Tian
 * @create: 2020-11-06 13:03
 **/
public class UserLoginDTO {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


package com.itmuch.msuser.auth;

/**
 * @program: ms-user
 * @description:
 * @author: Long Ao Tian
 * @create: 2020-11-06 13:28
 **/
public class SecurityException extends RuntimeException{
    public SecurityException(Throwable cause) {
        super(cause);
    }

    public SecurityException(String message) {
        super(message);
    }
}


package com.itmuch.msclass.auth;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @program: ms-class
 * @description:
 * @author: Long Ao Tian
 * @create: 2020-11-06 18:38
 **/

/**
 * Retention 指定注解的保留策略
 * RUNTIME  注解会在字节码中存在，并且可以通过反射获取
 * 元注解：注解在注解类上的注解
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ChechAuthz {

    /**
     * 判断用户角色
     * @return  返回用户角色
     */
    String hasRole();
}


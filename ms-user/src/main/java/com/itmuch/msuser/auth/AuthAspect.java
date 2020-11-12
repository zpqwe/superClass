package com.itmuch.msuser.auth;

import com.itmuch.msuser.jwt.JwtOperator;
import com.sun.org.apache.xml.internal.security.exceptions.XMLSecurityRuntimeException;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.ServletRequestHandledEvent;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: ms-user
 * @description:
 * @author: Long Ao Tian
 * @create: 2020-11-06 13:21
 **/
@Aspect  //表示这是一个切面类
@Component
public class AuthAspect {

    @Autowired
    private JwtOperator jwtOperator;
    @Around("@annotation(com.itmuch.msuser.auth.Login)")
    public Object checkLogin(ProceedingJoinPoint point) {
        try {
            // 获取http请求中的Header(token)
            ServletRequestAttributes attributes = (ServletRequestAttributes) (RequestContextHolder.getRequestAttributes());
            HttpServletRequest request = attributes.getRequest();
            String token = request.getHeader("Authorization");
            if(StringUtils.isEmpty(token)){
                throw new SecurityException("Token没有传！");
            }

            // 校验token是否合法，合法认为用户已经登录，不可犯返回401
            Boolean aBoolean = this.jwtOperator.validateToken(token);
            if (!aBoolean){
                throw new SecurityException("Token 非法");
            }
            Claims userInto = this.jwtOperator.getClaimsFromToken(token);
            request.setAttribute("userId",userInto.get("userId"));
            request.setAttribute("username",userInto.get("username"));
            return point.proceed();
        } catch (Throwable throwable) {
            throw new SecurityException(throwable);
        }
    }
}


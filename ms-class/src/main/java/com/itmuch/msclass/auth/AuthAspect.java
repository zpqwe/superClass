package com.itmuch.msclass.auth;

import com.itmuch.msclass.jwt.JwtOperator;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @program: ms-user
 * @description:
 * @author: Long Ao Tian
 * @create: 2020-11-06 13:21
 **/
@Order(1) // 因为 buyByIdFallback 这个fallback与 GlobalExceptionHandler.java 全局异常有冲突,值越小越靠前执行
@Aspect  //表示这是一个切面类
@Component
public class AuthAspect {

    @Autowired
    private JwtOperator jwtOperator;
    @Around("@annotation(com.itmuch.msclass.auth.Login)")
    public Object checkLogin(ProceedingJoinPoint point) {
        try {
            this.validationToken();
            return point.proceed();
        } catch (Throwable throwable) {
            throw new SecurityException(throwable);
        }
    }

    @Around("@annotation(com.itmuch.msclass.auth.ChechAuthz)")
    public Object checkAuthz(ProceedingJoinPoint point) {
        try {
            HttpServletRequest request = validationToken();
            // 2.判断角色是否ok
            // 拿到token里面的role
            Object role = request.getAttribute("role");
            // 拿到注解里面的role
            MethodSignature signature = (MethodSignature) point.getSignature();
            Method method = signature.getMethod();
            ChechAuthz chechAuthz = method.getAnnotation(ChechAuthz.class);
            String roleInAnnotation = chechAuthz.hasRole();
            if(!Objects.equals(role,roleInAnnotation)){
                throw  new SecurityException("当前用户不具备"+roleInAnnotation+"的角色");
            }
            return point.proceed();
        } catch (Throwable throwable) {
            throw new SecurityException(throwable);
        }
    }

    private HttpServletRequest validationToken() {
        //1.校验Token
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
        request.setAttribute("role",userInto.get("role"));
        return request;
    }
}


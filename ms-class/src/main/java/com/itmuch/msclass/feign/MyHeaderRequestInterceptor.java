package com.itmuch.msclass.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: ms-class
 * @description:
 * @author: Long Ao Tian
 * @create: 2020-11-06 14:41
 **/
public class MyHeaderRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        // 获取http请求中的Header(token)
        ServletRequestAttributes attributes = (ServletRequestAttributes) (RequestContextHolder.getRequestAttributes());
        HttpServletRequest request = attributes.getRequest();
        String token = request.getHeader("Authorization");
        requestTemplate.header("Authorization",token);
    }
}


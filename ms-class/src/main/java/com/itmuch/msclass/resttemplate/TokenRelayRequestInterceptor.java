package com.itmuch.msclass.resttemplate;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @program: ms-class
 * @description:
 * @author: Long Ao Tian
 * @create: 2020-11-06 15:04
 **/
public class TokenRelayRequestInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        // 获取http请求中的Header(token)
        ServletRequestAttributes attributes = (ServletRequestAttributes) (RequestContextHolder.getRequestAttributes());
        HttpServletRequest servletRequest = attributes.getRequest();
        String token = servletRequest.getHeader("Authorization");

        HttpHeaders headers = request.getHeaders();
        headers.add("Authorization",token);
        // 保证请求继续执行...
        return execution.execute(request,body);
    }
}


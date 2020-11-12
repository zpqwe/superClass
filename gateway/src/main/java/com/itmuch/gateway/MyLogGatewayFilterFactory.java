package com.itmuch.gateway;

/**
 * @program: gateway
 * @description:
 * @author: Long Ao Tian
 * @create: 2020-11-05 12:48
 **/

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

/**
 * 需要用GatewayFilterFactory 作为后缀
 */
@Component
public class MyLogGatewayFilterFactory extends AbstractNameValueGatewayFilterFactory {
    public static final Logger LOGGER= LoggerFactory.getLogger(MyLogGatewayFilterFactory.class);
    @Override
    public GatewayFilter apply(NameValueConfig config) {
        //
        return ((exchange, chain) -> {
            LOGGER.info("请求进来了，key={},value={}",config.getName(),config.getValue());
            ServerHttpRequest modifiedRequest = exchange.getRequest()
                    .mutate()
                    .build();
            ServerWebExchange modifiedExchange = exchange.mutate()
                    .request(modifiedRequest)
                    .build();
            return chain.filter(modifiedExchange);
        });
    }
}


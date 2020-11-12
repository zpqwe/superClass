package com.itmuch.gateway;

/**
 * @program: gateway
 * @description:
 * @author: Long Ao Tian
 * @create: 2020-11-05 12:05
 **/

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * 路由谓词工厂必须以 RoutePredicateFactory 结尾
 *
 *  // 1.读取配置文件里面的配置，并注入到config参数里面来
 *  2.判断当前时间是否满足要求
 */
@Component
public class TimeBetweenRoutePredicateFactory
extends AbstractRoutePredicateFactory<TimeConfig> {

    public TimeBetweenRoutePredicateFactory() {
        super(TimeConfig.class);
    }

    @Override
    public Predicate<ServerWebExchange> apply(TimeConfig config) {
        LocalTime startTime = config.getStartTime();
        LocalTime endTime = config.getEndTime();
        LocalTime now = LocalTime.now();
        return serverWebExchange -> now.isAfter(startTime)&&now.isBefore(endTime);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("startTime","endTime");
    }

    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
        String format = formatter.format(LocalTime.now());
        System.out.println(format);
    }
}


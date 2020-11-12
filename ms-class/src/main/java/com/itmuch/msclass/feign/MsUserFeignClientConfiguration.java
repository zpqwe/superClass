package com.itmuch.msclass.feign;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * @program: ms-class
 * @description:
 * @author: Long Ao Tian
 * @create: 2020-10-28 22:02
 **/

/**
 * 这个类不要加@Configuration注解
 * 否则必须放到启动类所在的包以外(@ComponentScan能够扫描的包以外)
 * 否则就会被所有Feign Client 共享
 */
public class MsUserFeignClientConfiguration {
    @Bean
    public Logger.Level loggerLevel(){
        return Logger.Level.FULL;
    }

}


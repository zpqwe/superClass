package com.itmuch.msclass;

import com.itmuch.msclass.resttemplate.TokenRelayRequestInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@EnableFeignClients //(defaultConfiguration = MsUserFeignClientConfiguration.class)
@SpringBootApplication
@EnableBinding({Source.class})
public class MsClassApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsClassApplication.class, args);
    }

    /**
     *  spring web 轻量级 http client
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(
                Collections.singletonList(new TokenRelayRequestInterceptor())
        );
        return restTemplate;
    }
}

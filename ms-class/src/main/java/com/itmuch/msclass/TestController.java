package com.itmuch.msclass;

import com.itmuch.msclass.auth.ChechAuthz;
import com.itmuch.msclass.domain.dto.UserDTO;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import io.vavr.collection.Seq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @program: ms-class
 * @description:
 * @author: Long Ao Tian
 * @create: 2020-09-19 18:11
 **/
@RestController
public class TestController {

    @Autowired
    private DiscoveryClient discoveryClient;
    @GetMapping("/test-discovery")
    public List<ServiceInstance> testDiscovery(){
        // 到 consul 上查询指定微服务的所有实例
        return discoveryClient.getInstances("msuser");
    }

    @Autowired
    private RateLimiterRegistry rateLimiterRegistry;

    @GetMapping("/rate-limiter-configs")
    public Seq<RateLimiter> test(){
        return rateLimiterRegistry
                .getAllRateLimiters();
    }

    @Autowired
    private RestTemplate restTemplate;
    @GetMapping("/test-token-relay")
    public ResponseEntity<UserDTO> testTokenRelay(@RequestHeader("Authorization")String token){

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization",token);
        return this.restTemplate.exchange(
                "http://ms-user/users/{id}",
                HttpMethod.GET,
                new HttpEntity<String>(headers),
                UserDTO.class,
                1
        );
    }

    @GetMapping("/test-token-relay2")
    public UserDTO testTokenRelay2(){
       return this.restTemplate.getForObject(
                "http://ms-user/users/{id}",
                UserDTO.class,
                1
        );
    }

    /**
     * 当前仅当用户角色是VIP的时候才能访问
     * @return
     */
    @GetMapping("/vip")
    @ChechAuthz(hasRole = "vip")
    public UserDTO vip(){
        return this.restTemplate.getForObject(
                "http://ms-user/users/{id}",
                UserDTO.class,
                1
        );
    }

    @GetMapping("/test-sidecar")
    @Bulkhead(name="testSidecar")//实现api的容错  Resilience4j
    public String testSidecar(){
        // restTemplate整合了ribbon 服务发现，负载均衡
       return this.restTemplate.getForObject(
                "http://sidecar",
                String.class
        );
    }
}


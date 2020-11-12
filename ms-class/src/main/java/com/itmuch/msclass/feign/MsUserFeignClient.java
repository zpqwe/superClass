package com.itmuch.msclass.feign;

import com.itmuch.msclass.domain.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @program: ms-class
 * @description:
 * @author: Mr.Wang
 * @create: 2020-10-22 20:23
 **/
@FeignClient(name = "ms-user"/*,configuration = MsUserFeignClientConfiguration.class*/)
public interface MsUserFeignClient {

    @GetMapping("/users/{userId}")
    UserDTO findUserById(@PathVariable("userId")Integer userId);

   /* UserDTO userDTO = restTemplate.getForObject(
            "http://ms-user/users/{id}",
            UserDTO.class,
            userId
    );*/
}

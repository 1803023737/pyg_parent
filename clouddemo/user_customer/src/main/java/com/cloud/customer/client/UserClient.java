package com.cloud.customer.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//采用动态代理的方式生成接口的实现类  然后封装远程调用的代码  完成调用
//feign的配置
@FeignClient(value = "user-service",fallback = FallbackImpl.class)
public interface UserClient {

    @GetMapping("user/{id}")
    String queryUserById2(@PathVariable("id") Long id);


}

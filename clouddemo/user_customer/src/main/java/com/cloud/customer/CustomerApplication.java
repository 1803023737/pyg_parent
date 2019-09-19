package com.cloud.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

//服务熔断
//@EnableCircuitBreaker
//@EnableDiscoveryClient//负载均衡
//@SpringBootApplication

//启用feign
@EnableFeignClients(basePackages = "com.boot.customer.client")
@SpringCloudApplication//代替上面三个注解
public class CustomerApplication {

    @Bean
    @LoadBalanced//负载均衡
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class,args);
    }


}

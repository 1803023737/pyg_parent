package com.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;
//cloud注册中心不止一种eureka  好包括zk等   引用依赖  添加注解  增加配置
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.boot.user.mapping")//mybatis mapper扫描包
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class,args);

    }

}

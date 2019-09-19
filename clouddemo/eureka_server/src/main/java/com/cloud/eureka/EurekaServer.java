package com.cloud.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
// 引入eureka服务启动器  注解配置EnableEurekaServer 配置文件中配置
//标记为eureka的服务
@EnableEurekaServer
@SpringBootApplication
public class EurekaServer {

    public static void main(String[] args) {

        SpringApplication.run(EurekaServer.class,args);

    }

}

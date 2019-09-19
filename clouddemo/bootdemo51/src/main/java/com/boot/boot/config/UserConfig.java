package com.boot.boot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class UserConfig {

    @Value("${local.ip}")
    private String localIp;

    @Value("${local.port}")
    private Integer localPort;


    //@Value("${test.name}")
    //private String testName;


    @Autowired
    private Environment environment;

    public void show(){
        System.out.println("通过value注解获取："+localIp);//方法1
        System.out.println("通过value注解获取："+localPort);//方法1
        System.out.println("local.ip:"+environment.getProperty("local.ip"));//方法2

        System.out.println(environment.getProperty("name"));
        System.out.println(environment.getProperty("app.name"));

        //System.out.println("test name:======"+testName);

    }

}

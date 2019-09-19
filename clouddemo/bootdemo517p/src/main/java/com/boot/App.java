package com.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 * springboot  web容器的更换
 * 1.默认是tomcat 可以更换为jetty
 * 更换步骤
 * 1.排除tomcat依赖
 * 2.新增jetty依赖
 *
 *
 */
@SpringBootApplication
public class App {
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        SpringApplication.run(App.class,args);
    }
}

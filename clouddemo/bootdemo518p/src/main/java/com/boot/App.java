package com.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * 静态资源配置路径
 * 1.webapp下
 * 2.resources 下的   private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {"classpath:/META-INF/resources/", "classpath:/resources/","classpath:/static/", "classpath:/public/" };
 *
 *
 */

@ServletComponentScan
@SpringBootApplication
public class App
{
    public static void main(String[] args) {

        //启动springboot
        SpringApplication.run(App.class,args);


    }

}

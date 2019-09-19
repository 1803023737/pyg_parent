package com.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

/**
 * Hello world!‘
 *
 * 1.拦截器的使用
 * 2.异常处理  处理boot默认异常处理的配置 (exclude = ErrorMvcAutoConfiguration.class)
 *   自定义异常处理  错误页面配置类
 *
 *
 */
@SpringBootApplication(exclude = ErrorMvcAutoConfiguration.class)//排除boot封装的错误处理
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        SpringApplication.run(App.class,args);
    }
}

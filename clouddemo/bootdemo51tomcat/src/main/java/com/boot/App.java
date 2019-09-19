package com.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App 
{
    public static void main( String[] args )
    {
        //EmbeddedServletContainerCustomizer  1.0版本

        //WebServerFactoryCustomizer  2.0版本

        SpringApplication.run(App.class,args);
    }
}

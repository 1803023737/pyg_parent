package com.boot.day02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


//1.addInitializers
//2.context.Initializer.classes=com.boot.day02.context_initializer.MyApplicationContextInitializer 配置文件
//3.通过spring.factory方式

/*
* CommandLineRunner 容器启动成功之后最后一步进行回调
* 使用步骤：
* 1.写一个类  实现CommandLineRunner  接口
* 2.把实现类纳入spring容器中
* */



@SpringBootApplication
public class App3 {

    public static void main(String[] args) {

        //应用对象
        SpringApplication app = new SpringApplication(App3.class);
        //加入
       // app.addInitializers(new MyApplicationContextInitializer());
        //启动应用
        ConfigurableApplicationContext context = app.run(args);
        context.close();
    }

}

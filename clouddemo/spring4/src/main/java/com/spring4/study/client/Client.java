package com.spring4.study.client;

import com.spring4.study.di.Ball;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Client {

    public static void main(String[] args) {

        //指定扫描包
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext("com.spring4.study");
        Ball ball = (Ball) context.getBean("ball");
        System.out.println(ball);
        context.close();


    }
}

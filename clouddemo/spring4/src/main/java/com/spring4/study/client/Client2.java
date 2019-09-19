package com.spring4.study.client;

import com.spring4.study.di.Ball;
import com.spring4.study.di.BasketBall;
import com.spring4.study.scan.AnnoScan;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Client2 {

    public static void main(String[] args) {

        //指定扫描包
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(AnnoScan.class);
        Ball ball = (Ball) context.getBean("ball");
        System.out.println(ball);
        System.out.println(ball.getBasketBall());

       // BasketBall basketBall = (BasketBall) context.getBean("createBasketBall");
        BasketBall basketBall2 = (BasketBall) context.getBean(BasketBall.class);
        //System.out.println(basketBall);
        System.out.println(basketBall2);

        context.close();
    }
}

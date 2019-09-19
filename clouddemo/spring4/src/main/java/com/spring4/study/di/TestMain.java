package com.spring4.study.di;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestMain {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                //new AnnotationConfigApplicationContext(Myconfig.class,Ball.class);//注入user类
                new AnnotationConfigApplicationContext(Myconfig.class,Ball.class);//注入user类
        Ball ball = (Ball) context.getBean(Ball.class);
        System.out.println(ball);
        System.out.println(ball.getBasketBall());

        BasketBall basketBall = context.getBean(BasketBall.class);
        System.out.println(basketBall);
        context.close();
    }

}

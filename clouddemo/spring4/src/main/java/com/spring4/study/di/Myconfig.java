package com.spring4.study.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Myconfig {

    @Bean
    public BasketBall createBasketBall(){
        System.out.println("primary 注解优先！");
       return new BasketBall();
    }

    //@Bean
    //public BasketBall createBasketBall2(){
    //    return new BasketBall();
    //}
}

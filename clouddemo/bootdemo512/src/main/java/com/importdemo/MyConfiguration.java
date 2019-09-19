package com.importdemo;

import org.springframework.context.annotation.Bean;


public class MyConfiguration {

    @Bean
    public Animal animal(){
        return new Animal();
    }



}

package com.spring4.study_day3;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Bean1 {

    //初始化
    @PostConstruct
    public void init(){
        System.out.println("bean init!!");
    }
}

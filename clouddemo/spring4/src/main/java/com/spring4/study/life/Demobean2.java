package com.spring4.study.life;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class Demobean2 {

    @PostConstruct//初始化后
    public void init(){
        System.out.println("初始化bean。。。。"+this.getClass().getName());
    }

    @PreDestroy//销毁前
    public void destory(){
        System.out.println("销毁bean。。。。"+this.getClass().getName());
    }


}

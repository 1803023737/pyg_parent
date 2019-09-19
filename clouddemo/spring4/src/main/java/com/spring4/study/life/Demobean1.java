package com.spring4.study.life;

public class Demobean1 {

    public void init(){
        System.out.println("初始化bean。。。。"+this.getClass().getName());
    }

    public void destory(){
        System.out.println("销毁bean。。。。"+this.getClass().getName());
    }


}

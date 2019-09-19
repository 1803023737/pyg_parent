package com.spring4.study_day2;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

//spring判断实现接口ApplicationContextAware  自动将application对象通过set方法注入
@Component
public class Book implements ApplicationContextAware {

    private int num=0;

    //指定初始化方法！！！
    @PostConstruct
    public void init(){
        System.out.println("book 初始化。。。。");
        num=1;
    }

    private ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }

    public void show(){
        System.out.println("show book:"+applicationContext.getClass());
    }
}

package com.spring4.study_day2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

//spring判断实现接口ApplicationContextAware  自动将application对象通过set方法注入
@Component
public class Book2  {

    @Autowired
    private ApplicationContext applicationContext;

    public void show(){
        System.out.println("show book2:"+applicationContext.getClass());
    }
}

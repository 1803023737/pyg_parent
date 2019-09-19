package com.spring4.study_day2;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;


@Component
public class Book3 {

    //缺陷：当有多个构造函数  则spring会选取默认构造函数，applicationContext就会成为null值
    private ApplicationContext applicationContext;

    //public Book3() {
    //}

    //public Book3(ApplicationContext applicationContext) {
    //    this.applicationContext = applicationContext;
    //}
    public Book3(ApplicationContext applicationContext,Book book) {
        this.applicationContext = applicationContext;
    }

    public void show(){
        System.out.println("show book3:"+applicationContext.getClass());
    }
}

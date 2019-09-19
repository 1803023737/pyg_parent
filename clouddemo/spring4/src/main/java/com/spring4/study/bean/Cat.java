package com.spring4.study.bean;

import org.springframework.beans.factory.InitializingBean;

public class Cat implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("初始化装配bean！");
    }
}

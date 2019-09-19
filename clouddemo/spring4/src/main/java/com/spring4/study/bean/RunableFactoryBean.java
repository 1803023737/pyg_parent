package com.spring4.study.bean;

import org.springframework.beans.factory.FactoryBean;

public class RunableFactoryBean implements FactoryBean<MyBean2> {
    public MyBean2 getObject() throws Exception {
        return new MyBean2();
    }

    public Class<?> getObjectType() {
        return MyBean2.class;
    }

    public boolean isSingleton() {
        return true;
    }
}

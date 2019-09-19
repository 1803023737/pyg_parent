package com.boot.day02.context_initializer;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

//ApplicationContextInitializer  在容器fresh之前执行

public class MyApplicationContextInitializer2 implements ApplicationContextInitializer{
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        System.out.println("spring2  bean count:"+applicationContext.getBeanDefinitionCount());
       // ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        //beanFactory.getBean()
    }
}

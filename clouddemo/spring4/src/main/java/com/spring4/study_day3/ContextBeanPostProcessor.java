package com.spring4.study_day3;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class ContextBeanPostProcessor implements BeanPostProcessor {

    //bean初始化  init方法之前
    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        System.out.println("============postProcessBeforeInitialization=========="+o.getClass());
        return o;
    }

    //bean初始化  init方法之后
    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        System.out.println("============postProcessAfterInitialization=========="+o.getClass());
        return o;
    }
}

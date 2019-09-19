package com.spring4.study_day2;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

//@Component
public class EnhanceBeanPostProcessor implements BeanPostProcessor {
    //bean 属性设置之后 初始化方法之前
    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        System.out.println("=================postProcessBeforeInitialization============="+o.getClass());
        return o;
    }

    //bean 属性设置之后 初始化方法之后
    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        System.out.println("=================postProcessAfterInitialization============="+o.getClass());
        if (o instanceof Book){
            return new LogBook((Book) o);
        }
        return o;
    }
}

package com.spring4.study_day2;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ContextBeanPostProcessor implements BeanPostProcessor {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        System.out.println("======================postProcessBeforeInitialization======================"+o.getClass());
        if (o instanceof SpringContextAware){
            SpringContextAware sca= (SpringContextAware) o;
            ((SpringContextAware) o).setApplicationContext(applicationContext);
        }
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        System.out.println("======================postProcessAfterInitialization======================"+o.getClass());
        return o;
    }
}

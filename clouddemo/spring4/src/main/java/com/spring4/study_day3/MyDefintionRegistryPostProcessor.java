package com.spring4.study_day3;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

//BeanDefinitionRegistryPostProcessor 完成bean的注册到spring容器中
@Component
public class MyDefintionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        System.out.println("=================添加bean！======================");
        for (int i=0;i<=10;i++){
            //加入bean
            BeanDefinitionBuilder builder=BeanDefinitionBuilder.rootBeanDefinition(Person.class);
            //属性注入
            builder.addPropertyValue("name", "张家兄弟"+i);
            registry.registerBeanDefinition("person"+i, builder.getBeanDefinition());
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}

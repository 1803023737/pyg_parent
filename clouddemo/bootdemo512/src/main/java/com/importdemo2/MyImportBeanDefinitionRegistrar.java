package com.importdemo2;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        //自己手动注入对象
        BeanDefinitionBuilder builder=BeanDefinitionBuilder.rootBeanDefinition(Student.class);
        BeanDefinition beanDefinition=builder.getBeanDefinition();
        registry.registerBeanDefinition("student", beanDefinition);

    }
}

package com.enabledemo;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

//将EchoBeanPostProcessor 装到spring容器中
public class EchoImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        Map<String, Object> map = importingClassMetadata.getAnnotationAttributes(EnableEcho.class.getName());
        String[] packages = (String[]) map.get("packages");
        List packageList= Arrays.asList(packages);//数组转化为ist
        //打印下看看
        System.out.println("packageList:"+packageList);
        BeanDefinitionBuilder bdb=BeanDefinitionBuilder.rootBeanDefinition(EchoBeanPostProcessor.class);
        bdb.addPropertyValue("packageList", packageList);
        //装配EchoBeanPostProcessor  到spring容器中
        registry.registerBeanDefinition(EchoBeanPostProcessor.class.getName() ,bdb.getBeanDefinition() );
    }
}

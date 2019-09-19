package com.enabledemo;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;


//enable和import综合使用
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(EchoImportBeanDefinitionRegistrar.class)//导入这个类
public @interface EnableEcho {
    //就是打印装配packages的所有类
    String[] packages();

}

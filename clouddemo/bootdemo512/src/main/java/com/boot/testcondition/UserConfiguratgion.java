package com.boot.testcondition;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfiguratgion {

    //默认  需要读取属性 属性值没有的默认装配  有的话值必须是小黑，不然无法装配
    @Bean
    @ConditionalOnProperty(name = "dog.name",matchIfMissing = true,havingValue = "小黑")//是否存在属性配置
    public Dog createDog(){
        return new Dog();
    }

    @Bean
    @ConditionalOnClass(name="com.google.gson.Gson")//装配  存在gson类
    //@ConditionalOnClass(name="com.google.gson.xxx")//不存在这个类  就不装配cat
    public Cat createCat(){
        return new Cat();
    }

    @Bean
    @ConditionalOnBean(name="createCat")//name 对应bean的名字
    //@ConditionalOnBean(name="xxxx")
    public Bird createBird(){
        return new Bird();
    }

}

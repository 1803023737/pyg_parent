package com.spring4.study.config;

import com.spring4.study.bean.*;
import com.spring4.study.life.Demobean1;
import com.spring4.study.life.Demobean2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class MyConfig {

    @Bean
    @Scope("prototype")//多例模式
    public MyBean myBean() {
        return new MyBean();
    }

    //配置工厂
    @Bean
    public RunableFactoryBean factoryBean(){
        return new RunableFactoryBean();
    }

    //第二种bean工厂方式
    @Bean
    public BeanFactory2 create(){
        return new BeanFactory2();
    }

    @Bean
    public MyBean3 createBean2(BeanFactory2 beanFactory2){
        return beanFactory2.creatBean2();
    }

    @Bean
    public Cat createCat(){
        return new Cat();
    }

    //生命周期-===============================================
    @Bean(initMethod = "init",destroyMethod = "destory")
    public Demobean1 createDemobean1(){
        return new Demobean1();
    }

    //生命周期-===============================================
    @Bean
    public Demobean2 createDemobean2(){
        return new Demobean2();
    }

}

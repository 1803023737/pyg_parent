package com.boot.boot.config2;

//测试不同激活的配置文件  配置不同的bean

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootConfiguration
@Profile("test")//整个类都是dev环境激活才产生  第一层拦截类上  第二层拦截是方法上
public class Conf {

    @Bean
    public Dog createDog() {
        System.out.println("=========1=======");
        return new Dog();
    }

    @Bean
    @Profile("test")
    public Dog createDog2() {
        System.out.println("=========2=======");
        return new Dog();
    }

    @Bean
    @Profile("dev")
    public Dog createDog3() {
        System.out.println("=========3=======");
        return new Dog();
    }
}

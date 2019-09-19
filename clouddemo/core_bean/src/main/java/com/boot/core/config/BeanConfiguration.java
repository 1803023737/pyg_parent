package com.boot.core.config;

import com.boot.core.bean.Corebean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//模拟启动器   springboot 自动驻入对象
@Configuration
public class BeanConfiguration {

    @Bean
    public Corebean corebean(){
        return new Corebean();
    }

}

package com.boot.core.config;

import com.boot.core.bean.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfiguration {

    @Bean
    public User createUser(){
        return new User();
    }

}

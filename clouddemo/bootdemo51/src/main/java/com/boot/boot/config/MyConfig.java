package com.boot.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MyConfig {

    @Bean
    public List createList() {
        return new ArrayList();
    }


}

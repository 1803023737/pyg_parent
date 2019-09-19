package com.spring4.study_day3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public Bean1 create() {
        return new Bean1();
    }

}

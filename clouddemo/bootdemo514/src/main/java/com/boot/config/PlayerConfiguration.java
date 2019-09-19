package com.boot.config;

import com.boot.bean.James;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PlayerConfiguration {

   @Bean
    public James james(){
       return new James();
   }

}

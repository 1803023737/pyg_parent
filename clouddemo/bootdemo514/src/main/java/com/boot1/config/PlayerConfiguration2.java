package com.boot1.config;

import com.boot1.bean.Curry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PlayerConfiguration2 {

   @Bean
    public Curry curry(){
       return new Curry();
   }

}

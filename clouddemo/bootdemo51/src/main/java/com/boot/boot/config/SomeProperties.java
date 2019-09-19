package com.boot.boot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ConfigurationProperties()
public class SomeProperties {

    private List fruit=new ArrayList();

    private String [] animal=new String[5];


    public List getFruit() {
        return fruit;
    }

    public void setFruit(List fruit) {
        this.fruit = fruit;
    }

    public String[] getAnimal() {
        return animal;
    }

    public void setAnimal(String[] animal) {
        this.animal = animal;
    }
}

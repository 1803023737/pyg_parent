package com.importdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

//import  可以导入一个类  也可以导入configuration


@SpringBootApplication
//@Import({User.class,Role.class,MyConfiguration.class})
//@Import(MyImportSelector.class)
@EnableLog(name = "spring boot")
public class App {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
        //导入普通类
        User user = context.getBean(User.class);
        Role role = context.getBean(Role.class);
        System.out.println(user);
        System.out.println(role);
        //导入configuration
        Animal animal = context.getBean(Animal.class);
        System.out.println("animal:"+animal);
        context.close();
    }

}

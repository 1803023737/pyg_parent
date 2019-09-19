package com.importdemo2;

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
        Student student = context.getBean(Student.class);
        System.out.println("student:"+student);
        context.close();
    }

}

package com.boot.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class App2 {

    @Bean
    public Runnable createRunable() {
        return new Runnable() {
            @Override
            public void run() {
                System.out.println(" springboot is running!");
            }
        };
    }

    public static void main(String[] args) {

        //第一种方式
        SpringApplication springApplication = new SpringApplication(App2.class);
        //配置读取的文件的类型  激活配置  默认得配置也会加入
        springApplication.setAdditionalProfiles("test");
        ConfigurableApplicationContext applicationContext = springApplication.run(args);

        String appName = applicationContext.getEnvironment().getProperty("app.name");
        System.out.println("appName:"+appName);
        String appName2 = applicationContext.getEnvironment().getProperty("app.name2");
        System.out.println("appName2:"+appName2);


        //Dog dog = (Dog) applicationContext.getBean("createDog");
        //Dog dog2 = (Dog) applicationContext.getBean("createDog2");
        //Dog dog3 = (Dog) applicationContext.getBean("createDog3");
        //System.out.println(dog);
        //System.out.println(dog2);
        //System.out.println(dog3);
        applicationContext.close();
    }

}

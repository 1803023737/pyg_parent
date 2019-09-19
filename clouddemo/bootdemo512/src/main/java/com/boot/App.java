package com.boot;

import com.boot.enable.JdbcConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.sql.DataSource;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(App.class, args);

        //1.测试实现统一接口的实现类对象
        //Map<String, EncodingConvert> map = applicationContext.getBeansOfType(EncodingConvert.class);
        //for (String key : map.keySet()) {
        //    System.out.println("key:"+map.get(key));
        //}

        //2.测试实现统一接口的实现类对象
        //Dog dog = applicationContext.getBean(Dog.class);
        //Dog dog = (Dog) applicationContext.getBean("createDog");
        //System.out.println("dog:"+dog);

        //3.测试ConditionalOnClass注解
        //Cat cat = (Cat) applicationContext.getBean("createCat");
        //System.out.println(cat);

        //4.测试ConditionalOnBean注解
        //Bird bird = (Bird) applicationContext.getBean("createBird");
        //System.out.println(bird);

        //5.enable入门
        JdbcConfiguration jdbcConfiguration = (JdbcConfiguration) applicationContext.getBean("jdbcConfiguration");
        System.out.println(jdbcConfiguration.toString());

        //6enable入门2
        DataSource dataSource = (DataSource) applicationContext.getBean("dataSource");
        System.out.println(dataSource);
        applicationContext.close();
    }
}

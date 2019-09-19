package com.async;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.Map;

@SpringBootApplication
@EnableAsync
public class App {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
        Map<String, Runnable> map = context.getBeansOfType(Runnable.class);
        for (String key : map.keySet()) {
            System.out.println("key:"+key+",value:"+map.get(key));
        }
        //获取对象
        Runnable demo1 = context.getBean(Runnable.class);
        demo1.run();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end!!!");
        context.close();
    }

}

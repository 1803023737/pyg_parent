package com.boot;

import com.boot.bean.James;
import com.boot1.bean.Curry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.HashMap;
import java.util.Map;

/*
* 1.scanBasePackages  可以覆盖默认扫描包
* 2.exclude = GsonAutoConfiguration.class  exclude 排出的是自动配置的类   excludeName  排除配置类
*
*
* */

@SpringBootApplication(scanBasePackages = "com",excludeName = "org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration")
public class App {

    @Value("${server.host:localhost}")
    private  String serverHost;


    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(App.class);
        //springApplication.setBannerMode(Banner.Mode.OFF);
        //设置默认配置文件配置项
        Map<String, Object> defaultProperties =new HashMap<>();
        defaultProperties.put("server.host", "127.0.0.1");
        springApplication.setDefaultProperties(defaultProperties);
        //启动
        ConfigurableApplicationContext context = springApplication.run(args);
        //ConfigurableApplicationContext context = SpringApplication.run(App.class, args);

        James james = context.getBean(James.class);
        System.out.println("james:"+james);
        Curry curry = context.getBean(Curry.class);
        System.out.println("curry:"+curry);

        //gson
        //Gson gson = context.getBean(Gson.class);
        //System.out.println("gson:"+gson.getClass().getName());
        
        //获取配置项
        String server_Hsot = context.getEnvironment().getProperty("server.host");
        System.out.println("serverhost："+server_Hsot);
        System.out.println(context.getBean(App.class).serverHost);

        context.close();

    }

}

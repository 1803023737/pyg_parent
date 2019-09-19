package com.boot;

import com.boot.core.bean.Corebean;
import com.boot.core.bean.Role;
import com.boot.core.bean.User;
import com.google.gson.Gson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

//@EnableConfigurationProperties
//EnableAutoConfiguration  配置注解  从classpath：下搜索META-INF spring.factories的文件
//关键点 1 spring  AutoConfigurationImportSelector将他的返回值纳入spring容器
//2. SpringFactoriesLoader  从 META-INF spring.factories的文件 读取配置
//3. spring.boot.enableautoconfiguration 默认为true
//exclude = UserConfiguration.class  排除配置   两种排除方式

//@SpringBootApplication(exclude = UserConfiguration.class)//配置排除
//@SpringBootApplication(excludeName ="com.boot.core.config.UserConfiguration" )//配置排除
@SpringBootApplication
public class App {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
        Corebean corebean = context.getBean(Corebean.class);
        User user = context.getBean(User.class);
        Role role = context.getBean(Role.class);
        //注入成功！！！
        System.out.println("corebean:"+corebean);
        System.out.println("user:"+user);
        System.out.println("role:"+role);

        Gson gson = (Gson) context.getBean("gson");
        System.out.println("gson:"+gson.getClass().getName());
        context.close();
    }

}

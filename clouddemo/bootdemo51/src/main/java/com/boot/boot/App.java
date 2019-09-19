package com.boot.boot;

import com.boot.boot.config.DataSourceProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class App {

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
        SpringApplication springApplication = new SpringApplication(App.class);
        //配置读取的文件的类型  激活配置  默认得配置也会加入
        springApplication.setAdditionalProfiles("test");
        ConfigurableApplicationContext applicationContext = springApplication.run(args);

        //第二种方式
        //ConfigurableApplicationContext applicationContext = SpringApplication.run(App.class, args);
   /*     Runnable runnable = (Runnable) applicationContext.getBean("createRunable");
        runnable.run();
        System.out.println("==============获得配置对象=================");
        List list = applicationContext.getBean(List.class);
        System.out.println(list);
        System.out.println("==============获得配置文件=================");
        String ip = applicationContext.getEnvironment().getProperty("local.ip");
        System.out.println("ip:"+ip);
        System.out.println("==============获得配置文件2=================");
        UserConfig userConfig = applicationContext.getBean(UserConfig.class);
        userConfig.show();*/


        //UserConfig userConfig = applicationContext.getBean(UserConfig.class);
        //userConfig.show();

        //JdbcConfig jdbcConfig = (JdbcConfig) applicationContext.getBean("jdbcConfig");
        //System.out.println(jdbcConfig.toString());

        //获得连接池对象
        DataSourceProperties dp = (DataSourceProperties) applicationContext.getBean("dataSourceProperties");
        System.out.println("数据库连接池的相关信息："+ dp.toString());

        //注入list
 /*       SomeProperties someProperties = applicationContext.getBean(SomeProperties.class);
        System.out.println(someProperties.getFruit());
        //注入数组
        String[] animals = someProperties.getAnimal();
        for (String animal : animals) {
            System.out.println(animal);
        }*/

        //测试使用EnvironmentPostProcessor  来读取配置文件
      /*  String testName = applicationContext.getEnvironment().getProperty("test.name");
        System.out.println(testName);*/

      //不同环境下的app配置

        String appName = applicationContext.getEnvironment().getProperty("app.name");
        System.out.println("appName:"+appName);
        String ip = applicationContext.getEnvironment().getProperty("local.ip");
        System.out.println("ip:"+ip);
        applicationContext.close();
    }

}

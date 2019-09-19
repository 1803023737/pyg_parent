package com.enabledemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableEcho(packages = "com.enabledemo")
public class App {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(App.class, args);

        //1.首先  import  参数类被注入spring容器  EchoImportBeanDefinitionRegistrar
        //2.注入 EchoBeanPostProcessor类到spring容器中  这个会在所有的类加载spring前后执行
        context.close();
    }

}

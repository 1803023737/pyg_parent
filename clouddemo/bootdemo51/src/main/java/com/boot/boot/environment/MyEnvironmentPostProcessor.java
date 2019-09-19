package com.boot.boot.environment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.util.Properties;


//自定义将配置文件使用代码的形式注入
//使用场景  各个应用间将配置集中化  可以http请求配置信息，达到各个系统配置得到统一配置信息
@Component
public class MyEnvironmentPostProcessor implements EnvironmentPostProcessor {
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        try {
            FileInputStream in = new FileInputStream("C:/Users/dell/Desktop/1.properties");
            Properties properties=new Properties();
            properties.load(in);
            PropertiesPropertySource propertySource=new PropertiesPropertySource("my",properties);
            environment.getPropertySources().addLast(propertySource);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

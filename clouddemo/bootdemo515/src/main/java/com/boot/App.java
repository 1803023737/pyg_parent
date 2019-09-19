package com.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 运行流程
 * 1.判断是否为web环境
 * 2.加载classpath下面的META-INF/spring.factories文件的org.springframework.context.ApplicationContextInitializer
 *   到List<ApplicationContextInitializer<?>> initializers 容器中
 * 3.加载所有ApplicationListener
 * 4.推断main方法  Class<?> mainApplicationClass
 *====================以上是SpringApplication 实例化============================
 *
 * ====================run=====================================================
 *5.run 方法
 *6.设置  java.awt.headless的值
 *7. 加载classpath下面的META-INF/spring.factories文件的 SpringApplicationRunListener 的实现类
 * 8.执行SpringApplicationRunListener 的start方法
 * 9. applicationArguments对象  实例化
 * 10 ConfigurableEnvironment  实例化
 * 11.配置 ConfigurableEnvironment  主要是把run方法参数配置进去
 * 12.执行SpringApplicationRunListener 的environmentPrepared方法
 * 13 如果不是web项目环境  environment转化为非web环境的environment
 * 14.打印banner
 * 15.初始化applicationContext
 * 16.postProcessApplicationContext后置回调
 * 17.回调所有ApplicationContextInitializer的方法
 * 18.执行 listeners.contextPrepared(context);
 * 19.beanfactory注入 applicationArguments,printedBanner对象
 * 20.加载所有的源到context中去
 * 21.listeners.contextLoaded(context);
 *
 * 22.
 *
 */

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(App.class, args);

       // context.close();
    }

}

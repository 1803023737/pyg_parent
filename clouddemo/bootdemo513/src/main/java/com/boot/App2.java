package com.boot;

import com.boot.day01.listener.MyApplicationEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


//监听方式 1 app.addListeners(new MyApplicationListener());
// 2.MyApplicationListenerBaruspring管理
//3 配置文件方式  DelegatingApplicationListener参照
//4 书写 MyEventhandler  类上配置@Component 方法上配置 @EventListener   //后台自动生成listener  不需要手动写listener

@SpringBootApplication
public class App2 {

    public static void main(String[] args) {

        //应用对象
        SpringApplication app = new SpringApplication(App2.class);
        //配置
        //app.addListeners(new MyApplicationListener());
        //启动应用
        ConfigurableApplicationContext context = app.run(args);
        //发起事件
        context.publishEvent(new MyApplicationEvent(new Object()));

        context.stop();
        context.close();
    }

}

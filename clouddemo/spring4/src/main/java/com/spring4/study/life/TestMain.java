package com.spring4.study.life;

import com.spring4.study.bean2.User;
import com.spring4.study.config.MyConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

public class TestMain {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class, User.class);//注入user类
        //AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        //第一种方式
        Demobean1 bean = context.getBean(Demobean1.class);
        System.out.println(bean);
        //第二种方式
        Demobean2 demobean2 = context.getBean(Demobean2.class);
        System.out.println(demobean2);

        //打印user
        User user = context.getBean(User.class);
        System.out.println(user);
        User user2 = (User) context.getBean("myUser");
        System.out.println(user2);
        Map<String, User> map = context.getBeansOfType(User.class);
        System.out.println(map);
        context.close();
    }

}

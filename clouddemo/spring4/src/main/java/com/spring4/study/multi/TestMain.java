package com.spring4.study.multi;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

public class TestMain {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext( Son1.class,Son2.class);//注入user类
        //获得继承一个接口的所有子类并遍历
        Map<String, Parent> map = context.getBeansOfType(Parent.class);
        System.out.println(map);
        for (String key : map.keySet()) {
            System.out.println("key:"+key+",value:"+map.get(key));
        }
        context.close();
    }

}

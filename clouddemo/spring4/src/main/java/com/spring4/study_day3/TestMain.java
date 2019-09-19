package com.spring4.study_day3;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

public class TestMain {

    public static void main(String[] args) {
        //扫描包
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext("com.spring4.study_day3");
        //Person p = context.getBean(Person.class);
        //System.out.println("person："+p);

        Map<String, Person> personMap = context.getBeansOfType(Person.class);
        for (String key : personMap.keySet()) {
            System.out.println("key:"+key+",value:"+personMap.get(key));
        }
        System.out.println("==================================================");
        //context.getBeansOfType(Person.class).values().forEach(
        //        System.out::println
        //);
        context.close();

    }

}

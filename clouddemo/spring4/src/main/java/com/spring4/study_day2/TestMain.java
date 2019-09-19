package com.spring4.study_day2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestMain {

    public static void main(String[] args) {
        //扫描包
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext("com.spring4.study_day2");
        System.out.println("===============book===============");
        Book book = (Book) context.getBean("book");
        System.out.println(book);
        book.show();
        System.out.println("===============book2===============");
        Book2 book2 = (Book2) context.getBean("book2");
        System.out.println("book2:"+book2);
        book2.show();
        System.out.println("===============book3===============");
        Book3 book3 = (Book3) context.getBean("book3");
        System.out.println("book3:"+book3);
        book3.show();
        System.out.println("===============dog===============");
        Dog dog = (Dog) context.getBean("dog");
        System.out.println("dog:"+dog);
        context.close();

    }

}

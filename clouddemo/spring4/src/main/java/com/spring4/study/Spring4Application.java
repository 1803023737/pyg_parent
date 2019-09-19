package com.spring4.study;

import com.spring4.study.bean.*;
import com.spring4.study.config.MyConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Spring4Application {

    public static void main(String[] args) {
        //注解式容器
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(MyConfig.class);
        //获得容器中的bean
        MyBean myBean = (MyBean) context.getBean("myBean");//通过名字获取
        MyBean bean = context.getBean(MyBean.class);
        System.out.println(myBean==bean);
        System.out.println("===============================通过工厂bean获得对象=========================");
        MyBean2 myBean2 = context.getBean(MyBean2.class);
        System.out.println("mybean2:"+myBean2);
        MyBean2 factoryBean = (MyBean2) context.getBean("factoryBean");
        System.out.println("factoryBean:"+factoryBean);
        //获得工厂bean本身
        System.out.println("===============================获得工厂bean=================================");
        RunableFactoryBean runableFacbean1toryBean = context.getBean(RunableFactoryBean.class);
        System.out.println(runableFacbean1toryBean);
        RunableFactoryBean runableFactoryBean1 = (RunableFactoryBean) context.getBean("&factoryBean");
        System.out.println(runableFactoryBean1);

        //第二种工厂方式
        System.out.println("第二种工厂方式==============================");
        BeanFactory2 beanFactory2 = context.getBean(BeanFactory2.class);
        System.out.println(beanFactory2);
        MyBean3 myBean3 = context.getBean(MyBean3.class);
        System.out.println(myBean3);

        //获得cat  看是否执行初始化
        Cat cat = context.getBean(Cat.class);
        System.out.println(cat);

        //容器关闭
        context.close();
    }


}

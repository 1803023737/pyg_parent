package com.boot.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

//切面类
@Component
@Aspect
public class LogAspect {

    //前置通知
    @Before("execution(* com.boot.dao..*.*(..))")
    //切入点表达式：织入包及子包下所有的类的所有方法
    public void log(){
        //Class<?> aClass = AopContext.currentProxy().getClass();
        //System.out.println("获取代理对象类："+aClass);
        System.out.println("before log ! this is a log!");
    }

    //后置通知
    //JoinPoint point  可以获取执行的一些目标对象
    @After("execution(* com.boot.dao..*.*(..))")
    public void logAfter(JoinPoint point){
        System.out.println("After log ! this is a log!");
        Object target = point.getTarget();
        System.out.println("目标对象类："+target.getClass().getName());
        Object[] args = point.getArgs();
        System.out.println("参数为:"+Arrays.toString(args));
        //方法名
        String name = point.getSignature().getName();
        System.out.println("签名："+name);

    }




}

package com.boot;

import com.boot.dao.IUserDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Hello world!
 * aop
 * 1.加入依赖  默认就开启aop支持
 * 2.创建aspect  并且纳入spring容器中  配置通知方法@before 切入点表达式（只想切点的类，方法）
 *
 *
 * 查看 AopAutoConfiguration  boot自动配置aop
 *
 * spring.aop.auto  默认配置 true  启动aop配置
 *@EnableAspectJAutoProxy(exposeProxy = true)  可以获得代理对象的类  否则会报错
 *  //Class<?> aClass = AopContext.currentProxy().getClass();
    //System.out.println("获取代理对象类："+aClass);
 *
 */
//启用aop注解
@EnableAspectJAutoProxy(exposeProxy = true)
@SpringBootApplication
public class App 
{
    public static void main( String[] args )
    {
        ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
        IUserDao userDao = context.getBean(IUserDao.class);
        System.out.println("userdao:"+userDao);
        System.out.println("userdao class:"+userDao.getClass().getName());
        userDao.add("admin", "123");
    }
}

package com.boot.day01.listener;

import org.springframework.context.ApplicationListener;

/**
 * 泛型定义时间类型
 * 定义事件监听器
 */
//@Component
public class MyApplicationListener implements ApplicationListener<MyApplicationEvent> {

    @Override
    public void onApplicationEvent(MyApplicationEvent event) {
        System.out.println("接收到事件："+event.getClass().getName());
    }
}

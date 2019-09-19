package com.boot.day01.listener;

import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MyEventhandler{

    @EventListener
    public void event(MyApplicationEvent event) {
        System.out.println("接收到事件2："+event.getClass().getName());
    }

    @EventListener
    public void event(ContextClosedEvent event) {
        System.out.println("应用关闭事件："+event.getClass().getName());
    }
}

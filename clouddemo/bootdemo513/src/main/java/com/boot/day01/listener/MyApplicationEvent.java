package com.boot.day01.listener;

import org.springframework.context.ApplicationEvent;

/**
 * 定义事件  时间类型
 */
public class MyApplicationEvent extends ApplicationEvent  {

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
        public MyApplicationEvent(Object source) {
        super(source);
    }
}

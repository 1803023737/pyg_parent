package com.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class Demo1 implements Runnable{

    @Override
    @Async
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println("===================="+i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

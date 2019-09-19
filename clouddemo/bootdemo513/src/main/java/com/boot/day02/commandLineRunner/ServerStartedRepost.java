package com.boot.day02.commandLineRunner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Order(2)
@Component
public class ServerStartedRepost implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("==============启动成功！当前时间："+ LocalDateTime.now().toString());
    }
}

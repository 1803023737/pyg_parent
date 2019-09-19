package com.boot.day02.commandLineRunner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Component
public class ServerSuccessRepost implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("==============应用成功启动！==============");
    }
}

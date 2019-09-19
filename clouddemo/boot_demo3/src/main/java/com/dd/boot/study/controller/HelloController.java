package com.dd.boot.study.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping("hello1.html")
    public String Hello() {
        return "hello world!";
    }
}

package com.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @RequestMapping("/home")
    @ResponseBody
    public String hello(){
        System.out.println("欢迎来到springboot学习！");
        return "hello spring boot!";
    }

    @RequestMapping("/exeception")
    @ResponseBody
    public String exeception(){
        System.out.println("抛个异常玩一下");
        throw new ClassCastException("类转换异常");
        //return "hello spring boot!";
    }

}

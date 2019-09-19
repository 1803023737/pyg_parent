package com.boot.controller;

import com.boot.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
* 1.@GetMapping  @PostMapping
* 2.@RequestParam 将字段映射到方法形参上  required属性代表是否为必须字段！ 默认为true   defaultValue属性在参数缺失时的时候默认值
* 3.@PathVariable 从路径中获得参数
* 4.@RequestBody  限制参数application/json格式 {"id":1,"name":"张安"}  并且只适用与post请求
* 5.HttpServletRequest request, HttpServletResponse response  支持参数 request，response
*
*
* */


@Controller
public class HelloController {

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    @ResponseBody
    public User hello() {
        User user = new User();
        user.setId(1);
        user.setName("张三");
        return user;
    }


    @GetMapping(value = "/show")
    @ResponseBody
    public User show() {
        User user = new User();
        user.setId(1);
        user.setName("李四");
        return user;
    }


    //传参
    @GetMapping(value = "/login")
    @ResponseBody
    public String login(@RequestParam(value = "username",defaultValue = "admin") String username,@RequestParam(value = "pwd",required = false) String password) {
        System.out.println("username:"+username+",password:"+password);
        return "login success!";
    }

    //传参2
    @GetMapping(value = "/user/{id}")
    @ResponseBody
    public String display(@PathVariable(value = "id") String id) {
        System.out.println("id:"+id);
        return "user display "+id;
    }

    //传参3
    @RequestMapping(value = "/userinfo")
    @ResponseBody
    public String userinfo(@RequestBody(required = false) User user) {
        System.out.println(user);
        return "user info ";
    }

    @RequestMapping(value = "/edit")
    @ResponseBody
    public String edit(HttpServletRequest request, HttpServletResponse response) {
        String remoteHost = request.getRemoteHost();
        int remotePort = request.getRemotePort();
        String remoteUser = request.getRemoteUser();
        String remoteAddr = request.getRemoteAddr();
        System.out.println("remoteHost:"+remoteHost);
        System.out.println("remotePort:"+remotePort);
        System.out.println("remoteUser:"+remoteUser);
        System.out.println("remoteAddr:"+remoteAddr);
        return "user edit ";
    }


}

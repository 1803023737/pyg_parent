package com.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * jsp渲染页面  springboot
 * 默认不支持jsp
 */
@Controller
public class JspController {

    @RequestMapping("loginUser")
    public String loginUser(String username,String password){
        if (username.equals("admin")&& password.equals("123456")){
            return "ok";
        }
        return "fail";
    }

    @RequestMapping("loginUser2")
    public String loginUser2(Model model){
        model.addAttribute("username", "张三啊");
        return "login";
    }


}

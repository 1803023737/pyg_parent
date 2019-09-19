package com.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * freemarker 与springboot整合
 * 1.  spring-boot-starter-freemarker加入以来
 * 2 默认模板地址 DEFAULT_TEMPLATE_LOADER_PATH = "classpath:/templates/";  默认扩展名 DEFAULT_SUFFIX = ".ftl";
 */
@Controller
public class AccountController {

    @RequestMapping("freemarker")
    public String reg() {
        return "reg";
    }

    @RequestMapping("/logout")
    public String logout(Model model) {
        model.addAttribute("username", "张三丰");
        model.addAttribute("logout", "退出");
        return "/logout";
    }

}

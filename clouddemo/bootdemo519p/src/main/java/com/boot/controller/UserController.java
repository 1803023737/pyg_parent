package com.boot.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;

@RestController
public class UserController {

    //局部类异常处理  处理异常  方法可以接收参数 exception
    //但是这种写法只能处理当前类中的异常
    @ExceptionHandler
    public String error(HttpServletRequest request,Exception e){
        //System.out.println(e.getMessage());
        //Throwable throwable = e.fillInStackTrace();
        //StackTraceElement[] stackTrace = throwable.getStackTrace();
        //System.out.println(Arrays.toString(stackTrace));
        return e.getMessage();
    }

    @RequestMapping("user/error1")
    public String error1() throws FileNotFoundException {
        throw new FileNotFoundException("文件未找到！");
        //return "user list";
    }

    @RequestMapping("user/error2")
    public String error2() throws FileNotFoundException {
        throw new ClassCastException("类转换异常");
        //return "user list";
    }

}

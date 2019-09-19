package com.boot.exception_2;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

//全局异常处理器
@ControllerAdvice
public class GolbalExceptionHandler {

    //全局异常处理
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public String errorHandler(Exception ex) {
        return "golbal exception handler:"+ex.getMessage();
    }

    //全局异常处理  ---传递页面
    @ExceptionHandler(value = ClassCastException.class)
    public String errorHandler2(Exception ex) {
        return "404.html";
    }

}

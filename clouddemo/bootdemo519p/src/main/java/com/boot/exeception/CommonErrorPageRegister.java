package com.boot.exeception;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.http.HttpStatus;

//异常处理 页面配置类
//@Component
public class CommonErrorPageRegister implements ErrorPageRegistrar{

    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        ErrorPage error404=new ErrorPage(HttpStatus.NOT_FOUND,"/404.html");
        ErrorPage error500=new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,"/500.html");
        ErrorPage error=new ErrorPage(ClassCastException.class,"/cast_error.html");
        registry.addErrorPages(error404,error500,error);
    }
}

package com.boot.config;

import com.boot.interceptor.LogInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//拦截器配置类   这个类就相当于一个配置文件xml  或者 extends WebMvcConfigurerAdapter
//@Configuration
public class WebConfiguration  implements WebMvcConfigurer  {

    //注册添加拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册自定义拦截器
        registry.addInterceptor(new LogInterceptor());
    }
}

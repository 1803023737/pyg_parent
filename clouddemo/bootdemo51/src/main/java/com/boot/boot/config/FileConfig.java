package com.boot.boot.config;

import org.springframework.stereotype.Component;

@Component
//可以配置从硬盘中读取，也可以从classpath读取  不是从application.properties  必须配置这个注解
//@PropertySources({@PropertySource("classpath:ds.properties"),@PropertySource("file:/C:/Users/dell/Desktop/1.properties")})
public class FileConfig {



}

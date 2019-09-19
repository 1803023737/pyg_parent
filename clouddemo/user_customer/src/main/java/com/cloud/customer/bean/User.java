package com.cloud.customer.bean;

import lombok.Data;

import java.util.Date;

//通用mapper 无xml配置sql，那么需要的是通过bean 来生成sql  那么就必须类上做一些相关的注解来实现sql的生成

//get set方法注解
//表注解
@Data
public class User {

    //主键注解   主键类型与数据库类型一定要对应！不然容易出现通用mapper 的sql出错！
    private Long id;

    private String username;
    private String password;
    private String phone;

    private Date created;


    private String note;


}

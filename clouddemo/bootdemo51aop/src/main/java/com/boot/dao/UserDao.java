package com.boot.dao;

import org.springframework.stereotype.Repository;

@Repository
public class UserDao implements IUserDao{

    public void add(String username,String password){
        System.out.println("username:"+username+",password:"+password);
    }


}

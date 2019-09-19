package com.cloud.user.service;

import com.cloud.user.mapper.UserMapper;
import com.cloud.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;


    public User queryByUserId(Long id){
        return userMapper.selectByPrimaryKey(id);
    }

    //增加注解  事务搞定
    @Transactional
    public void insertUser(User user){
         userMapper.insert(user);
    }
}

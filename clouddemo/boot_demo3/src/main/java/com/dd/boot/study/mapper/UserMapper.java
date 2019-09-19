package com.dd.boot.study.mapper;

import com.dd.boot.study.entity.User;
import org.springframework.stereotype.Repository;
 
/**
 * @Author:wjup
 * @Date: 2018/9/26 0026
 * @Time: 15:20
 */
@Repository
public interface UserMapper {
 
    User Sel(int id);
}

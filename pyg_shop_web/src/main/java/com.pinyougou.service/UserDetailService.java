package com.pinyougou.service;

import com.pinyougou.pojo.TbSeller;
import com.pinyougou.sellergoods.service.SellerService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

//用户认证类
public class UserDetailService implements UserDetailsService {

    //配置式的获取服务
    private SellerService sellerService;

    public void setSellerService(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    /**
     * @param username 用户登录名
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("==============UserDetailService=================");
        //角色集合
        List<GrantedAuthority> list = new ArrayList<>();
        //构建角色列表
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_SELLER");
        list.add(authority);//如果没有配置权限会出现403错误
        //返回用户对象  返回的这个是正确用户   与用户登陆的比对   角色进行资源权限控制
        TbSeller tbSeller = sellerService.findOne(username);
        if (tbSeller != null) {
            if (tbSeller.getStatus().equals("1")) {//已审核
                return new User(username, tbSeller.getPassword(), list);
            }
        }
        return null;
    }
}

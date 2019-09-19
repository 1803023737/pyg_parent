package com.cloud.customer.client;

import org.springframework.stereotype.Component;

//熔断接口实现类
@Component
    public class FallbackImpl implements UserClient {
    @Override
    public String queryUserById2(Long id) {
        String msg="服务器暂时繁忙！";
        return msg;
    }
}

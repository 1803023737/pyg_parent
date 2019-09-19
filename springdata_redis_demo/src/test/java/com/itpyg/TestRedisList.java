package com.itpyg;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-redis.xml"})
public class TestRedisList {

    @Autowired
    private RedisTemplate redisTemplate;


    @Before
    public void init(){
        //默认序列化 JdkSerializationRedisSerializer
        //修改序列化规则
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
    }

    @Test
    public void setValue(){

        BoundListOperations boundListOperations = redisTemplate.boundListOps("spring-list");
        boundListOperations.rightPush("r1");
        boundListOperations.rightPush("r2");
        boundListOperations.rightPush("r3");
        boundListOperations.rightPush("r4");
        boundListOperations.leftPush("l1");
        boundListOperations.leftPush("l2");
        boundListOperations.leftPush("l3");
        boundListOperations.leftPush("l4");
    }

    @Test
    public void getSetvalue(){
        BoundListOperations boundListOperations = redisTemplate.boundListOps("spring-list");
        System.out.println(boundListOperations.size());
        System.out.println(boundListOperations.range(0,-2));
    }

    @Test
    public void removeValue(){


        BoundListOperations boundListOperations = redisTemplate.boundListOps("spring-list");
        Object index = boundListOperations.index(1);
        System.out.println(index);
        //boundListOperations.remove(1,"r1");//删除几个r1
        //System.out.println(boundListOperations.size());
        //System.out.println(boundListOperations.range(0,-1));
    }

    @Test
    public void delValue(){

    }


}

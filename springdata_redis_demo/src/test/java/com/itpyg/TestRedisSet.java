package com.itpyg;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-redis.xml"})
public class TestRedisSet {

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

        BoundSetOperations boundSetOperations = redisTemplate.boundSetOps("spring-set");
        boundSetOperations.add("1");
        boundSetOperations.add("2");
    }

    @Test
    public void getSetvalue(){
        BoundSetOperations boundSetOperations = redisTemplate.boundSetOps("spring-set");
        Set members = boundSetOperations.members();
        System.out.println(members);
    }

    @Test
    public void removeValue(){
        BoundSetOperations boundSetOperations = redisTemplate.boundSetOps("spring-set");
        boundSetOperations.remove("1");
    }

    @Test
    public void delValue(){
         redisTemplate.delete("spring-set");
    }


}

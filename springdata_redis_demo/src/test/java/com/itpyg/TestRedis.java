package com.itpyg;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext-redis.xml")
public class TestRedis {

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

        BoundValueOperations bvo = redisTemplate.boundValueOps("test");
        //存值
        bvo.set("springdate-redis");
    }

    @Test
    public void getValue(){

        BoundValueOperations bvo = redisTemplate.boundValueOps("test");
        //存值
        String value = (String) bvo.get();
        System.out.println(value);
    }

    @Test
    public void deleteValue(){
         redisTemplate.delete("test");
    }


}

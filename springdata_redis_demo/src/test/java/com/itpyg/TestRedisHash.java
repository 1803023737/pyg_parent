package com.itpyg;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-redis.xml"})
public class TestRedisHash {

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
        BoundHashOperations boundHashOperations = redisTemplate.boundHashOps("spring-hash");
        boundHashOperations.put("a","aa");
        boundHashOperations.put("b","bb");
        boundHashOperations.put("c","cc");
    }

    @Test
    public void getSetvalue(){
        BoundHashOperations boundHashOperations = redisTemplate.boundHashOps("spring-hash");
        Set keys = boundHashOperations.keys();
        System.out.println(keys);
        for (Object key : keys) {
            System.out.println(boundHashOperations.get(key));
        }
    }

    @Test
    public void removeValue(){

        BoundHashOperations boundHashOperations = redisTemplate.boundHashOps("spring-hash");
        boundHashOperations.delete("c");
    }

    @Test
    public void delValue(){

    }


}

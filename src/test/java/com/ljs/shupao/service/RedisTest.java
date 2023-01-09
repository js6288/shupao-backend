package com.ljs.shupao.service;

import com.ljs.shupao.model.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;

@SpringBootTest
public class RedisTest {

    @Resource
    private RedisTemplate redisTemplate;

//    @Resource
//    private StringRedisTemplate stringRedisTemplate;

    @Test
    void test(){
        ValueOperations valueOperations = redisTemplate.opsForValue();

        //增
        valueOperations.set("linjsString","dog");
        valueOperations.set("linjsInt",2);
        valueOperations.set("linjsDouble",2.5);
        User user = new User();
        user.setId(5L);
        user.setUsername("shupao");
        valueOperations.set("linjsUser",user);
        //查
        Object linjsString = valueOperations.get("linjsString");
        Assertions.assertTrue("dog".equals(linjsString));
        Object linjsInt = valueOperations.get("linjsInt");
        Assertions.assertTrue(2 == (Integer) linjsInt);
        Object linjsDouble = valueOperations.get("linjsDouble");
        Assertions.assertTrue(2.5 == (Double)linjsDouble);
        User linjsUser = (User) valueOperations.get("linjsUser");
        System.out.println(linjsUser);

        redisTemplate.delete("linjsString");

    }
}

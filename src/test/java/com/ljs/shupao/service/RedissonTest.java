package com.ljs.shupao.service;

import com.ljs.shupao.model.domain.User;
import org.junit.jupiter.api.Test;
import org.redisson.api.RList;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class RedissonTest {

    @Resource
    private RedissonClient redissonClient;

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Test
    public void test(){

        //List。数据存在本地JVM内存中
        ArrayList<User> list = new ArrayList<>();
        User user = new User();
        user.setId(1L);
        user.setUsername("ljs");
        list.add(user);
        System.out.println("list: "+list.get(0));


        //数据存在redis的内存中
        RList<User> rList = redissonClient.getList("r-list");
        rList.add(user);
        System.out.println("rList: "+rList.get(0));


    }

    @Test
    public void test2(){
        ArrayList<User> list = new ArrayList<>();
        User user = new User();
        user.setId(1L);
        user.setUsername("ljs");
        list.add(user);

        ListOperations<String, Object> listOperations = redisTemplate.opsForList();
        listOperations.rightPush("listTest",list);

        Object listTest = listOperations.index("listTest", 0);
        System.out.println(listTest);
    }


    @Test
    void testWatchDog(){
        RLock lock = redissonClient.getLock("yupao:precachejob:docache:lock");
        try{
            //只有一个线程能获取到锁
            if (lock.tryLock(0,-1, TimeUnit.MILLISECONDS)){
                Thread.sleep(300000);
                System.out.println("getLock: "+Thread.currentThread().getId());
            }
        }catch(InterruptedException e){
            System.out.println(e.getMessage());
        }finally {
            //只能释放自己的锁
            if (lock.isHeldByCurrentThread()){
                System.out.println("unlock: "+Thread.currentThread().getId());
                lock.unlock();
            }
        }
    }
}

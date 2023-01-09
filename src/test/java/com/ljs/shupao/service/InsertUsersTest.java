package com.ljs.shupao.service;

import com.ljs.shupao.model.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@SpringBootTest
public class InsertUsersTest {

    @Resource
    private UserService userService;

    private ExecutorService executorService =
            new ThreadPoolExecutor(60,
                    1000,
                    10000,
                    TimeUnit.MILLISECONDS,
                    new ArrayBlockingQueue<>(10000)
                    );

    /**
     * 盐值，混淆密码
     */
    private static final String SALT = "yupi";

    //private ExecutorService executorService = new ThreadPoolExecutor(40,1000,10000, TimeUnit.MINUTES,new ArrayBlockingQueue<>(10000));

    @Test
    public void doInsertUsers(){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        final int INSERT_NUM=100000;
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < INSERT_NUM; i++) {
            User user = new User();
            user.setUsername("假用户");
            user.setUserAccount("fake_xiaoxiaoshu");
            user.setAvatarUrl("https://www.miyoushe.com/mainPage/bh2-logo-v2.png");
            user.setGender(0);
            user.setUserPassword(DigestUtils.md5DigestAsHex((SALT + "123456789").getBytes())); //123456789
            user.setPhone("13788556648");
            user.setEmail("test@testmail.com");
            user.setUserStatus(0);
            user.setIsDelete(0);
            user.setUserRole(0);
            user.setPlanetCode("111111");
            user.setTags("[]");
            userList.add(user);
        }
        userService.saveBatch(userList,10000);//16244
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());
    }

    /**
     * 多线程
     */
    @Test
    public void doConcurrencyInsertUsers(){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
//        final int INSERT_NUM=100000;
        int j = 0;
        int batchSize = 5000;
        List<CompletableFuture<Void>> futureList = new ArrayList<>();
        for (int i = 0;i<20;i++){
            List<User> userList = new ArrayList<>();
            do {
                j++;
                User user = new User();
                user.setUsername("假用户");
                user.setUserAccount("fake_xiaoxiaoshu");
                user.setAvatarUrl("https://www.miyoushe.com/mainPage/bh2-logo-v2.png");
                user.setGender(0);
                user.setUserPassword(DigestUtils.md5DigestAsHex((SALT + "123456789").getBytes())); //123456789
                user.setPhone("13788556648");
                user.setEmail("test@testmail.com");
                user.setUserStatus(0);
                user.setIsDelete(0);
                user.setUserRole(0);
                user.setPlanetCode("111111");
                user.setTags("[]");
                userList.add(user);
            } while (j % batchSize != 0);

            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                System.out.println("threadName:" + Thread.currentThread().getName());
                userService.saveBatch(userList, batchSize);
            },executorService);
            futureList.add(future);
        }
        CompletableFuture.allOf(futureList.toArray(new CompletableFuture[]{})).join();

        //
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());
    }

}

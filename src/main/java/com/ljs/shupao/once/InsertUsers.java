package com.ljs.shupao.once;

import com.ljs.shupao.mapper.UserMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class InsertUsers {

    /**
     * 盐值，混淆密码
     */
    private static final String SALT = "yupi";

    @Resource
    private UserMapper userMapper;

//    @Scheduled
//    public void doInsertUsers(){
//        final int INSERT_NUM = 1000;
//        StopWatch stopWatch = new StopWatch();
//        stopWatch.start();
//        for (int i = 0; i < INSERT_NUM; i++) {
//            User user = new User();
//            user.setUsername("假用户");
//            user.setUserAccount("fake_xiaoxiaoshu");
//            user.setAvatarUrl("https://www.miyoushe.com/mainPage/bh2-logo-v2.png");
//            user.setGender(0);
//            user.setUserPassword(DigestUtils.md5DigestAsHex((SALT + "123456789").getBytes())); //123456789
//            user.setPhone("13788556648");
//            user.setEmail("test@testmail.com");
//            user.setUserStatus(0);
//            user.setIsDelete(0);
//            user.setUserRole(0);
//            user.setPlanetCode("111111");
//            user.setTags("[]");
//
//        }
//        stopWatch.stop();
//        System.out.println(stopWatch.getTotalTimeMillis());
//    }
}

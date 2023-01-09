package com.ljs.shupao;

import com.ljs.shupao.model.domain.User;
import com.ljs.shupao.service.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class ShupaoApplicationTests {

    @Resource
    private UserService userService;

    @Test
    void testDigest() throws NoSuchAlgorithmException {
        String newPassword= DigestUtils.md5DigestAsHex(("abcd" + "mypassword").getBytes());
        System.out.println(newPassword);
    }


    @Test
    void contextLoads() {

    }

    @Test
    public void searchUserByTags(){
        List<String> list = Arrays.asList("java", "python");
        List<User> users = userService.searchUserByTags(list);
        Assert.assertNotNull(users);
    }

}

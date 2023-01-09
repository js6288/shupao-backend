package com.ljs.shupao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.ljs.shupao.mapper")
@EnableScheduling
public class ShupaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShupaoApplication.class, args);
    }

}

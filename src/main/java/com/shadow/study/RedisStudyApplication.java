package com.shadow.study;

import com.shadow.study.listener.ApplicationStartUpListener;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.shadow.study.dao")
public class RedisStudyApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(RedisStudyApplication.class);
        springApplication.addListeners(new ApplicationStartUpListener());
        springApplication.run();
    }
}


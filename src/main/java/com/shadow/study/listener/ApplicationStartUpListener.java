package com.shadow.study.listener;

import com.shadow.study.service.user.UserService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class ApplicationStartUpListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        UserService userService = contextRefreshedEvent.getApplicationContext().getBean(UserService.class);
        userService.loadUserToRedis();
    }
}

package com.shadow.study.controller;

import com.shadow.study.config.RedisPoolConfig;
import com.shadow.study.model.User;
import com.shadow.study.service.redis.RedisService;
import com.shadow.study.service.user.UserService;
import com.shadow.study.utils.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.UUID;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private RedisClient redisClient;

    @ResponseBody
    @PostMapping("/add")
    public int addUser(User user){
        return userService.addUser(user);
    }

    @ResponseBody
    @GetMapping("/getAll")
    public Object findAllUser(
        @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                    int pageNum,
        @RequestParam(name = "pageSize", required = false, defaultValue = "10")
                    int pageSize){

        redisService.set("redis_string_test", "springboot redis test");
        String result = redisService.get("redis_string_test");
        System.out.println(result);

        redisClient.set("pool_test", "pool string");
        String poolRes = redisClient.get("pool_test");
        System.out.println(poolRes);
        return userService.findAllUser(pageNum,pageSize);
    }
}

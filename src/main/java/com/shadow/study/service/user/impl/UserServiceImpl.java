package com.shadow.study.service.user.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shadow.study.config.RedisPoolConfig;
import com.shadow.study.dao.UserDao;
import com.shadow.study.model.User;
import com.shadow.study.service.user.UserService;
import com.shadow.study.utils.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Autowired
    private RedisClient redisClient;

    @Override
    public int addUser(User user) {
        return 0;
    }

    @Override
    public PageInfo<User> findAllUser(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> user = userDao.selectUsers();
        PageInfo result = new PageInfo(user);

        System.out.println(redisClient.get("users"));

        return result;
    }

    @Override
    public int loadUserToRedis() {
        List<User> users = userDao.selectUsers();
        if(users != null) {
            redisClient.set("users", users);
            System.out.println("初始化用户信息到redis!");
            return users.size();
        }
        else {
            return 0;
        }
    }

}

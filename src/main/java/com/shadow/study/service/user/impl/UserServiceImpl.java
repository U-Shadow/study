package com.shadow.study.service.user.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shadow.study.config.RedisPoolConfig;
import com.shadow.study.dao.UserDao;
import com.shadow.study.model.User;
import com.shadow.study.service.user.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public int addUser(User user) {
        return 0;
    }

    @Override
    public PageInfo<User> findAllUser(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> user = userDao.selectUsers();
        PageInfo result = new PageInfo(user);

        return result;
    }

}

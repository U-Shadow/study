package com.shadow.study.service.user;

import com.github.pagehelper.PageInfo;
import com.shadow.study.model.User;

public interface UserService {

    int addUser(User user);

    PageInfo<User> findAllUser(int pageNum, int pageSize);

    int loadUserToRedis();
}

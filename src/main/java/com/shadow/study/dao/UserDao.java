package com.shadow.study.dao;

import com.shadow.study.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {

    int insert(User record);

    List<User> selectUsers();
}


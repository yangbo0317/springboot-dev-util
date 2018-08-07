package com.yangbo.springboot.springbootmq.com.yangbo.dao;

import com.yangbo.springboot.springbootmq.com.yangbo.controller.User;

import java.util.List;

/**
 * yangbo
 * 2018-08-07
 * springboot-dev-util
 **/
public interface UserDao {
    List<User> findUserListByName(User user);
    int insertUser(User user);
}

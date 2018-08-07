package com.yangbo.springboot.springbootmq.com.yangbo.dao.impl;

import com.yangbo.springboot.springbootmq.com.yangbo.controller.User;
import com.yangbo.springboot.springbootmq.com.yangbo.dao.UserDao;
import com.yangbo.springboot.springbootmq.com.yangbo.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * yangbo
 * 2018-08-07
 * springboot-dev-util
 **/
@Component
public class UserDaoImpl implements UserDao {
    @Resource
    private UserMapper userMapper;
    @Override
    public List<User> findUserListByName(User user) {
        if(user==null|| StringUtils.isBlank(user.getName())){
            return null;
        }
        List<User> userList = userMapper.findByName(user.getName());
        return userList;
    }

    @Override
    public int insertUser(User user) {
        return userMapper.insert(user.getName(),user.getGender());
    }
}

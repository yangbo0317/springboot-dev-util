package com.yangbo.springboot.springbootmq.com.yangbo.controller;

import com.yangbo.springboot.springbootmq.com.yangbo.mapper.UserMapper;
import com.yangbo.springboot.springbootmq.com.yangbo.utils.JackJsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * yangbo
 * 2018-07-18
 * springboot-dev-util
 **/
@RestController
@RequestMapping("/user")
public class UserController {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JackJsonUtil jackJsonUtil;

    private static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value="addUser")
    public String addUser(@RequestBody User user){
        userMapper.insert(user.getName(),user.getGender());
        return "";
    }
    @RequestMapping(value="getUser")
    public String getUser(@RequestParam(value="name") String name){
        LOGGER.info("name="+name);
        List<User> list =userMapper.findByName(name);
        System.out.println(list.size());
        return jackJsonUtil.writeString(list);
    }

}

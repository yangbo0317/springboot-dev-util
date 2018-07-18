package com.yangbo.springboot.springbootmq.com.yangbo.controller;

import com.yangbo.springboot.springbootmq.com.yangbo.mapper.UserMapper;
import com.yangbo.springboot.springbootmq.com.yangbo.utils.JackJsonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * yangbo
 * 2018-07-18
 * springboot-dev-util
 **/
@RestController
@RequestMapping("/user")
@Api("UserController相关api")
public class UserController {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JackJsonUtil jackJsonUtil;

    private static Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @ApiOperation("用户注册")
    @RequestMapping(value="addUser",method=RequestMethod.POST)
    public String addUser(@RequestBody User user){
        userMapper.insert(user.getName(),user.getGender());
        return "";
    }
    @ApiOperation("获取用户信息")
    @RequestMapping(value="getUser",method= RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code=400,message="请求参数没填好"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对")
    })
    public String getUser(@RequestParam(value="name") String name){
        LOGGER.info("name="+name);
        List<User> list =userMapper.findByName(name);
        System.out.println(list.size());
        return jackJsonUtil.writeString(list);
    }

}

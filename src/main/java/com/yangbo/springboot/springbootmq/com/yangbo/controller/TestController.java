package com.yangbo.springboot.springbootmq.com.yangbo.controller;

import com.yangbo.springboot.springbootmq.com.yangbo.mapper.UserMapper;
import com.yangbo.springboot.springbootmq.com.yangbo.rabbitmq.provider.QueueEnum;
import com.yangbo.springboot.springbootmq.com.yangbo.rabbitmq.provider.RabbitMqSender;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * yangbo
 * 2018-04-14
 * springboot-mq1
 **/
@RestController
public class TestController {
    @Resource
    private RabbitMqSender rabbitMqSender;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private UserMapper userMapper;
    @RequestMapping(value = "hello")
    public String testHello(@RequestBody User user){
        try {
            System.out.println(user.getName());
            rabbitMqSender.sendNoticeMsg("notice.normal111","1");
        }catch (Exception  e){

        }
        System.out.println();
        return "hello";
    }

    @RequestMapping(value="adduser")
    public String addUser(@RequestBody User user){
        userMapper.insert(user.getName(),user.getGender());
        return "";
    }
    @RequestMapping(value="getUser")
    public String getUser(@RequestParam(value="name") String name){
        List<User> list =userMapper.findByName(name);
        System.out.println(list.size());
        return "";
    }

}

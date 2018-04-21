package com.yangbo.springboot.springbootmq.com.yangbo.controller;

import com.yangbo.springboot.springbootmq.com.yangbo.rabbitmq.provider.QueueEnum;
import com.yangbo.springboot.springbootmq.com.yangbo.rabbitmq.provider.RabbitMqSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * yangbo
 * 2018-04-14
 * springboot-mq1
 **/
@RestController
public class TestController {
    @Resource
    private RabbitMqSender rabbitMqSender;
    @RequestMapping(value = "hello")
    public String testHello(){

        rabbitMqSender.sendNoticeMsg("notice.normal","1");
        return "hello";
    }
}

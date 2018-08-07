package com.yangbo.springboot.springbootmq.com.yangbo.controller;

import lombok.Data;

import java.util.Date;

/**
 * yangbo
 * 2018-07-13
 * springboot-dev-util
 **/
@Data
public class User {
    private Long id;
    private String name;
    private Integer gender;
    private String address;
    private Date createTime;
    private Date updateTime;
    public User(){

    }
    public User(String name,Integer gender,String address){
        this.name = name;
        this.gender = gender;
        this.address = address;
    }

}

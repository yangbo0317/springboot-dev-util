package com.yangbo.springboot.springbootmq.com.yangbo.mapper;

import com.yangbo.springboot.springbootmq.com.yangbo.controller.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * yangbo
 * 2018-07-18
 * springboot-dev-util
 **/
@Mapper
public interface UserMapper {

    @Select("SELECT * FROM USER WHERE NAME = #{name}")
    List<User> findByName(@Param("name") String name);

    @Insert("INSERT INTO USER(NAME, GENDER,create_time,update_time) VALUES(#{name}, #{gender},NOW(),NOW())")
    int insert(@Param("name") String name, @Param("gender") Integer gender);

}

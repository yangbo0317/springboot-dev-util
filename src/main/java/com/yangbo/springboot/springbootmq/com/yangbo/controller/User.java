package com.yangbo.springboot.springbootmq.com.yangbo.controller;

/**
 * yangbo
 * 2018-07-13
 * springboot-dev-util
 **/
public class User {
    private String name;
    private Student student;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}

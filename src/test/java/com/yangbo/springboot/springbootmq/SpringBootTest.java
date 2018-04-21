package com.yangbo.springboot.springbootmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * yangbo
 * 2018-04-21
 * springboot-mq
 **/
@RunWith(SpringRunner.class)
@org.springframework.boot.test.context.SpringBootTest
public class SpringBootTest {
    @Autowired
    private JavaMailSender javaMailSender;
    @Test
    public void sendmail(){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("1370151513@qq.com");
        simpleMailMessage.setTo("2252625006@qq.com");
        simpleMailMessage.setSubject("测试油价");
        simpleMailMessage.setText("lallalal");
        javaMailSender.send(simpleMailMessage);



    }
}

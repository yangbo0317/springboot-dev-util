package com.yangbo.springboot.springbootmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

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
    public void sendmail() throws MessagingException {
        /**
         * 简单发送
         */
        //SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        //simpleMailMessage.setFrom("1370151513@qq.com");
        //simpleMailMessage.setTo("2252625006@qq.com");
        //simpleMailMessage.setSubject("测试油价");
        //simpleMailMessage.setText("lallalal");
        //javaMailSender.send(simpleMailMessage);
        /*
         * 带附件的邮件发送
         */
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
        mimeMessageHelper.setFrom("1370151513@qq.com");
        mimeMessageHelper.setTo("2252625006@qq.com");
        mimeMessageHelper.setSubject("邮件，带有附件");
        mimeMessageHelper.setText("<html><body><img src='cid:qqimage' ></body></html>",true);
        FileSystemResource fileSystemResource = new FileSystemResource(new File("/Users/yangbo/Desktop/WX20180421-130339@2x.png"));
        //mimeMessageHelper.addAttachment("附件",fileSystemResource);
        mimeMessageHelper.addInline("qqimage",fileSystemResource);
        javaMailSender.send(mimeMessage);
    }
}

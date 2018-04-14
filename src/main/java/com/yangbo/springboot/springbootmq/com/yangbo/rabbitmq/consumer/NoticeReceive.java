package com.yangbo.springboot.springbootmq.com.yangbo.rabbitmq.consumer;

import com.rabbitmq.client.Channel;
import com.yangbo.springboot.springbootmq.com.yangbo.rabbitmq.provider.QueueEnum;
import com.yangbo.springboot.springbootmq.com.yangbo.rabbitmq.provider.RabbitMqSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * yangbo
 * 2018-04-12
 **/
@Component
public class NoticeReceive {
    private static final Logger LOGGER = LoggerFactory.getLogger(NoticeReceive.class);

    @Resource
    private RabbitMqSender rabbitMqSender;
    @Autowired
    private RedisTemplate redisTemplate;

    @RabbitListener(queues = QueueConstant.NOTICE_DIRECT)
    public void direct(Message message, Channel channel) throws IOException {
        try {
            Integer a = 1/0;
            LOGGER.info("direct test-----------------Receiver : {}", new String(message.getBody()));
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            /**
             * 此处处理异常消息逻辑
             */
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            Integer count = (Integer) redisTemplate.opsForValue().get("yangbo-test");
            if(count==null){
                count=0;
            }

            redisTemplate.opsForValue().set("yangbo-test",count++);
            if(count==1){
                rabbitMqSender.sendMsgByDirect(QueueEnum.TTL_QUEUE_ONE_LEVEL.getBindKey(),new String(message.getBody()));
            }else if(count==2){
                rabbitMqSender.sendMsgByDirect(QueueEnum.TTL_QUEUE_TWO_LEVEL.getBindKey(),new String(message.getBody()));
            }else if(count==3){
                rabbitMqSender.sendMsgByDirect(QueueEnum.TTL_QUEUE_THREE_LEVEL.getBindKey(),new String(message.getBody()));
            }else{
                rabbitMqSender.sendMsgByDirect(QueueEnum.DEAD_LINE_QUEUE.getBindKey(),new String(message.getBody()));
            }
        }
    }
}

package com.yangbo.springboot.springbootmq.com.yangbo.rabbitmq.config;

import com.yangbo.springboot.springbootmq.com.yangbo.rabbitmq.provider.QueueEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 功能描述: 通知队列
 *
 * @param:
 * @auther: yangbo
 * @date: 2018/4/14
 * @return:
 */
@Configuration
@AutoConfigureAfter(RabbitMqConfig.class)
public class NoticeQueueConfig {

    @Bean
    public Queue noticeQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = QueueBuilder.durable(QueueEnum.NOTICE_QUEUE.getName()).build();
        rabbitAdmin.declareQueue(queue);

        return queue;
    }

    @Bean
    Binding bindingNoticeQueue(Queue noticeQueue, TopicExchange topicExchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(noticeQueue).to(topicExchange)
                .with(QueueEnum.NOTICE_QUEUE.getBindKey());
        rabbitAdmin.declareBinding(binding);

        return binding;
    }
}

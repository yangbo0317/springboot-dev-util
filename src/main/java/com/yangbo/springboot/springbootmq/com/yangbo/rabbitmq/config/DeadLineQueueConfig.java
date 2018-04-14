package com.yangbo.springboot.springbootmq.com.yangbo.rabbitmq.config;

import com.yangbo.springboot.springbootmq.com.yangbo.rabbitmq.provider.ExchangeEnum;
import com.yangbo.springboot.springbootmq.com.yangbo.rabbitmq.provider.QueueEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述: 死信队列
 *
 * @param:
 * @auther: yangbo
 * @date: 2018/4/14
 * @return:
 */
@Configuration
@AutoConfigureAfter(RabbitMqConfig.class)
public class DeadLineQueueConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeadLineQueueConfig.class);

    @Bean
    public Queue deadLineQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = QueueBuilder.durable(QueueEnum.DEAD_LINE_QUEUE.getName()).build();
        rabbitAdmin.declareQueue(queue);
        return queue;
    }
    @Bean
    Binding bindingDeadLineQueue(Queue deadLineQueue, DirectExchange directExchange, RabbitAdmin rabbitAdmin) {
        Binding bindingOneLevelTTLQueue = BindingBuilder.bind(deadLineQueue).to(directExchange)
                .with(QueueEnum.DEAD_LINE_QUEUE.getBindKey());
        rabbitAdmin.declareBinding(bindingOneLevelTTLQueue);
        LOGGER.debug("DEAD_LINE_QUEUE 与交换机绑定完成");

        return bindingOneLevelTTLQueue;
    }

    /**
     * 功能描述:  一级延迟队列
     *
     * @param: [rabbitAdmin]
     * @auther: yangbo
     * @date: 2018/4/13
     * @return: org.springframework.amqp.core.Queue
     */
    @Bean
    public Queue oneLevelTTLQueue(RabbitAdmin rabbitAdmin) {
        Map<String, Object> args = new HashMap<>(3);
        args.put("x-dead-letter-exchange", ExchangeEnum.DEAD_LINE.getCode());
        args.put("x-dead-letter-routing-key",  QueueEnum.TTL_QUEUE_ONE_LEVEL.getBindKey());
        args.put("x-message-ttl",  10000);
        Queue oneLevelTTLQueue = QueueBuilder.durable(QueueEnum.TTL_QUEUE_ONE_LEVEL.getName()).withArguments(args).build();
        rabbitAdmin.declareQueue(oneLevelTTLQueue);
        return oneLevelTTLQueue;
    }

    @Bean
    Binding bindingOneLevelTTLQueue(Queue oneLevelTTLQueue, DirectExchange directExchange, RabbitAdmin rabbitAdmin) {
        Binding bindingOneLevelTTLQueue = BindingBuilder.bind(oneLevelTTLQueue).to(directExchange)
                .with(QueueEnum.TTL_QUEUE_ONE_LEVEL.getBindKey());
        rabbitAdmin.declareBinding(bindingOneLevelTTLQueue);
        LOGGER.debug("TTL_QUEUE_ONE_LEVEL 与交换机绑定完成");

        return bindingOneLevelTTLQueue;
    }
    /**
     * 功能描述:  二级延迟队列
     *
     * @param: [rabbitAdmin]
     * @auther: yangbo
     * @date: 2018/4/13
     * @return: org.springframework.amqp.core.Queue
     */
    @Bean
    public Queue twoLevelTTLQueue(RabbitAdmin rabbitAdmin) {
        Map<String, Object> args = new HashMap<>(3);
        args.put("x-dead-letter-exchange", ExchangeEnum.DEAD_LINE.getCode());
        args.put("x-dead-letter-routing-key",  QueueEnum.TTL_QUEUE_TWO_LEVEL.getBindKey());
        args.put("x-message-ttl",  20000);
        Queue oneLevelTTLQueue = QueueBuilder.durable(QueueEnum.TTL_QUEUE_TWO_LEVEL.getName()).withArguments(args).build();
        rabbitAdmin.declareQueue(oneLevelTTLQueue);
        return oneLevelTTLQueue;
    }

    @Bean
    Binding bindingTwoLevelTTLQueue(Queue twoLevelTTLQueue, DirectExchange directExchange, RabbitAdmin rabbitAdmin) {
        Binding bindingTwoLevelTTLQueue = BindingBuilder.bind(twoLevelTTLQueue).to(directExchange)
                .with(QueueEnum.TTL_QUEUE_TWO_LEVEL.getBindKey());
        rabbitAdmin.declareBinding(bindingTwoLevelTTLQueue);
        LOGGER.debug("TTL_QUEUE_TWO_LEVEL 与交换机绑定完成");

        return bindingTwoLevelTTLQueue;
    }
    /**
     * 功能描述:  三级级延迟队列
     *
     * @param: [rabbitAdmin]
     * @auther: yangbo
     * @date: 2018/4/13
     * @return: org.springframework.amqp.core.Queue
     */
    @Bean
    public Queue threeLevelTTLQueue(RabbitAdmin rabbitAdmin) {
        Map<String, Object> args = new HashMap<>(2);
        args.put("x-dead-letter-exchange", ExchangeEnum.DEAD_LINE.getCode());
        args.put("x-dead-letter-routing-key",  QueueEnum.TTL_QUEUE_THREE_LEVEL.getBindKey());
        args.put("x-message-ttl",  30000);
        Queue threeLevelTTLQueue = QueueBuilder.durable(QueueEnum.TTL_QUEUE_THREE_LEVEL.getName()).withArguments(args).build();
        rabbitAdmin.declareQueue(threeLevelTTLQueue);
        return threeLevelTTLQueue;
    }

    @Bean
    Binding bindingThreeLevelTTLQueue(Queue threeLevelTTLQueue, DirectExchange directExchange, RabbitAdmin rabbitAdmin) {
        Binding bindingThreeLevelTTLQueue = BindingBuilder.bind(threeLevelTTLQueue).to(directExchange)
                .with(QueueEnum.TTL_QUEUE_THREE_LEVEL.getBindKey());
        rabbitAdmin.declareBinding(bindingThreeLevelTTLQueue);
        LOGGER.debug("TTL_QUEUE_THREE_LEVEL 与交换机绑定完成");

        return bindingThreeLevelTTLQueue;
    }
}

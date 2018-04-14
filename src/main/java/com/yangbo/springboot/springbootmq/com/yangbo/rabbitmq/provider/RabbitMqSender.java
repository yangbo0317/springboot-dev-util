package com.yangbo.springboot.springbootmq.com.yangbo.rabbitmq.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * 功能描述:
 *
 * @param:
 * @auther: yangbo
 * @date: 2018/4/14
 * @return:
 */
@Component
public class RabbitMqSender {

    private static final Logger log = LoggerFactory.getLogger(RabbitMqSender.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;


    /**
     * 发送消息
     *
     * @param routeKey
     * @param object
     */
    public void sendMsgByDirect(final String routeKey, final Object object) {
        sendMessage(ExchangeEnum.DIRECT, routeKey, object);
    }
    /**
     * 发送通知消息
     *
     * @param routeKey
     * @param object
     */
    public void sendNoticeMsg(final String routeKey, final Object object) {
        sendMessage(ExchangeEnum.DEAD_LINE, routeKey, object);
    }

    /**
     * 发送消息
     *
     * @param exchange 交换机
     * @param routeKey 路由键
     * @param object   消息内容
     */
    public void sendMessage(final ExchangeEnum exchange, final String routeKey, final Object object) {
        sendMessage(exchange.getCode(), routeKey, object);
    }


    /**
     * 发送消息
     *
     * @param exchange 交换机
     * @param routeKey 路由键
     * @param object   消息内容
     */
    public void sendMessage(final String exchange, final String routeKey, final Object object) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        log.debug("send message, exchange: {}, routeKey: {}, object: {}, correlationData: {}", exchange,
                routeKey, object, correlationData.getId());
        this.rabbitTemplate.convertAndSend(exchange, routeKey, object, correlationData);
    }

}
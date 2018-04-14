package com.yangbo.springboot.springbootmq.com.yangbo.rabbitmq.config;

import com.yangbo.springboot.springbootmq.com.yangbo.rabbitmq.provider.ExchangeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.ContentTypeDelegatingMessageConverter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 功能描述: MQ 配置类
 *
 * @param:
 * @auther: yangbo
 * @date: 2018/4/14
 * @return:
 */
@Configuration
public class RabbitMqConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMqConfig.class);

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter);
        template.setEncoding("UTF-8");
        template.setMandatory(true);
        template.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            String correlationId = message.getMessageProperties().getCorrelationIdString();
            LOGGER.info("消息：{} 发送失败，应答码：{} 原因：{} 交换机：{} 路由键：{}", correlationId, replyCode, replyText, exchange, routingKey);
        });
        template.setConfirmCallback((correlationData, ack, cause) -> {
            if (ack) {
                LOGGER.info("消息发送到exchange成功, id: {}", correlationData);
            } else {
                LOGGER.info("消息发送到exchange失败, 原因: {}", cause);
            }
        });

        return template;
    }

    @Bean
    public MessageConverter messageConverter() {
        return new ContentTypeDelegatingMessageConverter(new Jackson2JsonMessageConverter());
    }

    /**
     * 直连型交换机
     *
     * @param rabbitAdmin
     * @return
     */
    @Bean
    public DirectExchange directExchange(RabbitAdmin rabbitAdmin) {
        DirectExchange directExchange = (DirectExchange) ExchangeBuilder.directExchange(ExchangeEnum.DIRECT.getCode()).durable(true).build();
        rabbitAdmin.declareExchange(directExchange);
        LOGGER.debug("完成direct交换机bean实例化");

        return directExchange;
    }

    /**
     * 主题交换机
     *
     * @param rabbitAdmin
     * @return
     */
    @Bean
    public TopicExchange topicExchange(RabbitAdmin rabbitAdmin) {

        TopicExchange topicExchange = (TopicExchange) ExchangeBuilder.topicExchange(ExchangeEnum.DEAD_LINE.getCode()).durable(true).build();
        rabbitAdmin.declareExchange(topicExchange);
        LOGGER.debug("完成dlxExchange交换机bean实例化");

        return topicExchange;
    }

}
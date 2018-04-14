package com.yangbo.springboot.springbootmq.com.yangbo.rabbitmq.provider;

/**
 * 功能描述:
 *
 * @param:
 * @auther: yangbo
 * @date: 2018/4/14
 * @return:
 */
public enum ExchangeEnum {
    /**
     * 直连型交换机
     */
    DIRECT("notice.direct.exchange", "直连型交换机"),
    /**
     * 死信交换机
     */
    DEAD_LINE("notice.dlx.topic.exchange", "死信交换机");

    private String code;
    private String name;

    ExchangeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
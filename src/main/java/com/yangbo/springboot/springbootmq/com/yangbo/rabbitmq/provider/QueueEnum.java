package com.yangbo.springboot.springbootmq.com.yangbo.rabbitmq.provider;

/**
 * 功能描述:  队列定义枚举类
 *
 * @param:
 * @auther: yangbo
 * @date: 2018/4/14
 * @return:
 */
public enum QueueEnum {
    /**
     * 通知队列
     */
    NOTICE_QUEUE(ExchangeEnum.DIRECT.getCode(), "notice.queue", "notice.*"),
    /**
     * 通知死信队列
     */
    DEAD_LINE_QUEUE(ExchangeEnum.DEAD_LINE.getCode(), "notice.dlx.queue", "dead.line.roukey"),
    /**
     * 一级延迟队列
     */
    TTL_QUEUE_ONE_LEVEL(ExchangeEnum.DEAD_LINE.getCode(), "notice.ttl.one.level.queue", "notice.one"),
    /**
     * 二级延迟队列
     */
    TTL_QUEUE_TWO_LEVEL(ExchangeEnum.DEAD_LINE.getCode(), "notice.ttl.two.level.queue", "notice.two"),
    /**
     * 三级延迟队列
     */
    TTL_QUEUE_THREE_LEVEL(ExchangeEnum.DEAD_LINE.getCode(), "notice.ttl.three.level.queue", "notice.three");

    /**
     * 交换名称
     */
    private String exchange;
    /**
     * 队列名称
     */
    private String name;
    /**
     * 路由键
     */
    private String bindKey;

    QueueEnum(String exchange, String name, String bindKey) {
        this.exchange = exchange;
        this.name = name;
        this.bindKey = bindKey;
    }

    public String getName() {
        return name;
    }


    public String getBindKey() {
        return bindKey;
    }

}

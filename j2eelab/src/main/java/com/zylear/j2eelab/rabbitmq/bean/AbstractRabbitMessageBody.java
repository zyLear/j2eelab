package com.zylear.j2eelab.rabbitmq.bean;

import java.util.UUID;

/**
 * Created by yaoqijun on 2017-06-16.
 */
public abstract class AbstractRabbitMessageBody implements RabbitMessageBody {

    private String messageId;

    private Long timestamp;

    private Long expirationSecond;

    public AbstractRabbitMessageBody(){
        this.messageId = UUID.randomUUID().toString();
        this.timestamp = System.currentTimeMillis();
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Long getExpirationSecond() {
        return expirationSecond;
    }

    public void setExpirationSecond(Long expirationSecond) {
        this.expirationSecond = expirationSecond;
    }
}

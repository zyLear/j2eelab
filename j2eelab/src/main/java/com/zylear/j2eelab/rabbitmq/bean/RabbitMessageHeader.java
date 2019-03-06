package com.zylear.j2eelab.rabbitmq.bean;

public interface RabbitMessageHeader {

    Long getCreate_time();

    void setCreate_time(Long createtime);

    String getMessage_id();

    void setMessage_id(String messageId);

    String getType();

    void setType(String kind);
}
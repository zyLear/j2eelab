package com.zylear.j2eelab.rabbitmq.bean;

/**
 * Created by liutingfang on 2018/3/2.
 */
public class GenericRabbitMsgBody<H extends RabbitMessageHeader, B extends RabbitMessageBody> {

    private H header;
    private B body;

    public GenericRabbitMsgBody() {}

    public H getHeader() {
        return header;
    }

    public GenericRabbitMsgBody setHeader(H header) {
        this.header = header;
        return this;
    }

    public B getBody() {
        return body;
    }

    public GenericRabbitMsgBody setBody(B body) {
        this.body = body;
        return this;
    }
}
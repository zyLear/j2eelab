package com.zylear.j2eelab.mq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

/**
 * Created by xiezongyu on 2018/8/22.
 */
@Component
public class OneListener implements MessageListener {


    private static final String DEFAULT_CHARSET = "UTF-8";

    @Override
    public void onMessage(Message message) {
        MessageProperties messageProperties = message.getMessageProperties();
        String encoding = messageProperties.getContentEncoding();
        if (encoding == null) {
            encoding = DEFAULT_CHARSET;
        }
        try {
            String content = new String(message.getBody(), encoding);
            System.out.println("receive msg: " + content);


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}

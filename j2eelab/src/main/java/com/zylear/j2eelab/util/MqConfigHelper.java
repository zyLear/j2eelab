package com.zylear.j2eelab.util;



import com.zylear.j2eelab.rabbitmq.params.MqConfigParam;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;

/**
 * Created by yangzirong on 8/15/2017.
 */
public class MqConfigHelper {

    public static ConnectionFactory newCachingConnectionFactory(MqConfigParam apiMqConfigParam, String virtualHost) {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses(apiMqConfigParam.getUrl());
        connectionFactory.setUsername(apiMqConfigParam.getUsername());
        connectionFactory.setPassword(apiMqConfigParam.getPasspword());
        connectionFactory.setVirtualHost(virtualHost);
        connectionFactory.setChannelCacheSize(25);
        return connectionFactory;
    }

    public static ConnectionFactory newCachingConnectionFactory(MqConfigParam apiMqConfigParam) {
        return newCachingConnectionFactory(apiMqConfigParam, apiMqConfigParam.getVhost());
    }
}

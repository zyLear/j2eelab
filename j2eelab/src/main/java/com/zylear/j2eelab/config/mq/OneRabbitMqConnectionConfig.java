package com.zylear.j2eelab.config.mq;


import com.zylear.j2eelab.rabbitmq.params.MqConfigParam;
import com.zylear.j2eelab.util.MqConfigHelper;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zhangyang on 2017/9/3.
 */
@Configuration
public class OneRabbitMqConnectionConfig {

    @Value("${rabbitmq.svc.url}")
    private String url;
    @Value("${rabbitmq.svc.username}")
    private String username;
    @Value("${rabbitmq.svc.password}")
    private String password;
    @Value("${rabbitmq.svc.vhost}")
    private String vhost;

    public final static String CONNECTION_FACTORY = "mpmgrSvcConnectionFactory";
    public final static String MQCONFIG_PARAM = "mpmgrSvcMqConfigParam";

    @Bean(name = MQCONFIG_PARAM)
    public MqConfigParam mqConfigParam(){
        MqConfigParam configParam = new MqConfigParam();
        configParam.setUrl(url);
        configParam.setVhost(vhost);
        configParam.setUsername(username);
        configParam.setPasspword(password);
        return configParam;
    }

    @Bean(name = CONNECTION_FACTORY)
    public ConnectionFactory connectFactory(
            @Qualifier(MQCONFIG_PARAM) MqConfigParam apiMqConfigParam) {
        return MqConfigHelper.newCachingConnectionFactory(apiMqConfigParam);
    }


}

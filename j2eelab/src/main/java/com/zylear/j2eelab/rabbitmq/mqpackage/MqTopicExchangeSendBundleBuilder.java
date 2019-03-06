package com.zylear.j2eelab.rabbitmq.mqpackage;


import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.util.StringUtils;

/**
 * Created by yangzirong on 8/15/2017.
 */
public class MqTopicExchangeSendBundleBuilder {
    private String topicExchange = null;
    private MessageConverter messageConverter;
    private ConnectionFactory connectionFactory;

    public MqTopicExchangeSendBundleBuilder(String topicExchange, MessageConverter messageConverter, ConnectionFactory connectionFactory) {
        this.topicExchange = topicExchange;
        this.messageConverter = messageConverter;
        this.connectionFactory = connectionFactory;
    }

    public MqTopicExchangeSendBundle buildBundle(){
        if (StringUtils.isEmpty(topicExchange) ) {
            throw new RuntimeException("exchange empty");
        }

        return doInitBundle();
    }

    private MqTopicExchangeSendBundle doInitBundle() {
        MqTopicExchangeSendBundle bundle = new MqTopicExchangeSendBundle();
        bundle.setConnectionFactory(this.connectionFactory);
        RabbitAdmin rabbitAdmin = new RabbitAdmin(this.connectionFactory);
        bundle.setRabbitAdmin(rabbitAdmin);

        TopicExchange topicExchange = new TopicExchange(this.topicExchange, true, false);
        rabbitAdmin.declareExchange(topicExchange);
        bundle.setTopicExchange(topicExchange);

        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setExchange(this.topicExchange);
        template.setMessageConverter(this.messageConverter);
        bundle.setTemplate(template);

        return bundle;
    }

    public static class MqTopicExchangeSendBundle{
        private RabbitTemplate template;
        private RabbitAdmin rabbitAdmin;
        private ConnectionFactory connectionFactory;
        private TopicExchange topicExchange;

        public RabbitTemplate getTemplate() {
            return template;
        }

        public void setTemplate(RabbitTemplate template) {
            this.template = template;
        }

        public RabbitAdmin getRabbitAdmin() {
            return rabbitAdmin;
        }

        public void setRabbitAdmin(RabbitAdmin rabbitAdmin) {
            this.rabbitAdmin = rabbitAdmin;
        }

        public ConnectionFactory getConnectionFactory() {
            return connectionFactory;
        }

        public void setConnectionFactory(ConnectionFactory connectionFactory) {
            this.connectionFactory = connectionFactory;
        }

        public TopicExchange getTopicExchange() {
            return topicExchange;
        }

        public void setTopicExchange(TopicExchange topicExchange) {
            this.topicExchange = topicExchange;
        }
    }
}

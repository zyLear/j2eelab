package com.zylear.j2eelab.config.mq;


import com.zylear.j2eelab.mq.OneListener;
import com.zylear.j2eelab.rabbitmq.params.MqListenParam;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.AbstractMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yangzirong on 12/28/2017.
 */
@Configuration
public class MqConfig {

    public static final String ONE_PREFIX = "one_test_v";
    public final static String ONE_VERSION = "1";

    public final static String SENDER = "oneSender";


//    @Bean
//    public AbstractMessageListenerContainer userActionListenerContainer(
//            @Value("${rabbitmq.svc.queue}") String queue,
//            @Value("${rabbitmq.svc.exchange}") String exchange,
//            OneListener messListener,
//            @Qualifier(OneRabbitMqConnectionConfig.CONNECTION_FACTORY) ConnectionFactory connectionFactory
//    ){
//        MqListenParam listenParam = new MqListenParam();
//        listenParam.setQueueName(queue);
//        listenParam.setTopicExchange(exchange);
//        listenParam.setRoutingKey(ONE_PREFIX + ONE_VERSION + ".#");
//
//        MqTopicQueueListenBundleBuilder builder = new MqTopicQueueListenBundleBuilder(
//                listenParam, messListener, connectionFactory
//        );
//        builder.setListenConcurrentConsumers(3);
//        MqTopicQueueListenBundle bundle = builder.initBundle();
//        return bundle.getListenerContainer();
//    }

    @Bean
    public AbstractMessageListenerContainer userActionListenerContainer(
            @Value("${rabbitmq.svc.queue}") String queueName,
            @Value("${rabbitmq.svc.exchange}") String exchange,
            OneListener messListener,
            @Qualifier(OneRabbitMqConnectionConfig.CONNECTION_FACTORY) ConnectionFactory connectionFactory
    ) {
        MqListenParam listenParam = new MqListenParam();
        listenParam.setQueueName(queueName);
        listenParam.setTopicExchange(exchange);
        listenParam.setRoutingKey(ONE_PREFIX + ONE_VERSION + ".#");

        Queue queue = new Queue(queueName);

        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitAdmin.declareQueue(queue);

        TopicExchange topicExchange = new TopicExchange(exchange, true, false);
        rabbitAdmin.declareExchange(topicExchange);

        Binding binding = BindingBuilder.bind(queue).to(topicExchange).with(listenParam.getRoutingKey());
        rabbitAdmin.declareBinding(binding);


        SimpleMessageListenerContainer listenerContainer = new SimpleMessageListenerContainer();
        listenerContainer.setConnectionFactory(connectionFactory);
        listenerContainer.setConcurrentConsumers(1);
        listenerContainer.setChannelTransacted(true);
        listenerContainer.setMessageListener(messListener);
        listenerContainer.setQueues(queue);

        return listenerContainer;


    }


    @Bean(name = SENDER)
    public RabbitTemplate sender(
            @Value("${rabbitmq.svc.exchange}") String exchange,
            @Qualifier(OneRabbitMqConnectionConfig.CONNECTION_FACTORY) ConnectionFactory connectionFactory) {

        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);

        TopicExchange topicExchange = new TopicExchange(exchange, true, false);
        rabbitAdmin.declareExchange(topicExchange);

        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setExchange(exchange);
//        rabbitTemplate.setRoutingKey(ONE_PREFIX + ONE_VERSION);
        rabbitTemplate.setMessageConverter(new OneConverter());
//        rabbitTemplate.setRoutingKey(ONE_PREFIX + ONE_VERSION);
        return rabbitTemplate;
    }


//    @Bean(name = SENDER, initMethod = "start", destroyMethod = "stop")
//    public AsyncRabbitRouteSender userActionMsgRabbitSender(
//            @Value("${rabbitmq.svc.exchange}") String exchange,
//            @Qualifier(OneRabbitMqConnectionConfig.CONNECTION_FACTORY) ConnectionFactory connectionFactory) {
//
//
//        MqTopicExchangeSendBundleBuilder builder = new MqTopicExchangeSendBundleBuilder(
//                exchange, new MqDefaultMsgConvert(ONE_PREFIX), connectionFactory
//        );
//
//        MqTopicExchangeSendBundle bundle = builder.buildBundle();
//
//        AsyncRabbitRouteSender sender = new AsyncRabbitRouteSender();
//        sender.setSenderName(SENDER);
//        sender.setThreadCount(3);
//        sender.setAmqpTemplate(bundle.getTemplate());
//
//        return sender;
//    }

    public static class OneConverter implements MessageConverter {

        protected static final String CHATSET = "UTF-8";

        @Override
        public Message toMessage(Object string, MessageProperties messageProperties) throws MessageConversionException {

            byte[] bytes = ((String) string).getBytes();

//            messageProperties.setContentType(MessageProperties.CONTENT_TYPE_JSON);
            messageProperties.setContentEncoding(CHATSET);
            messageProperties.setContentLength(bytes.length);
            Message message = new Message(bytes, messageProperties);
            return message;
        }

        @Override
        public Object fromMessage(Message message) throws MessageConversionException {
            throw new RuntimeException("nn");
        }
    }
}

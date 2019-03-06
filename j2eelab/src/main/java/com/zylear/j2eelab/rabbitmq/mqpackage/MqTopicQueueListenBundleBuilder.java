package com.zylear.j2eelab.rabbitmq.mqpackage;

import com.zylear.j2eelab.rabbitmq.params.MqListenParam;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.AbstractMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.util.StringUtils;

/**
 * 单Queue, Topic类型监听
 *
 * Created by yangzirong on 8/15/2017.
 */
public class MqTopicQueueListenBundleBuilder {
    private MqListenParam listenParam;
    private int listenConcurrentConsumers = 1;
    private MessageListener messageListener;
    private ConnectionFactory connectionFactory;

    public MqTopicQueueListenBundleBuilder(
            MqListenParam listenParam, MessageListener messageListener,
            ConnectionFactory connectionFactory
    ) {
        this.listenParam = listenParam;
        this.messageListener = messageListener;
        this.connectionFactory = connectionFactory;
    }

    public MqTopicQueueListenBundle initBundle(){
        if ( StringUtils.isEmpty(listenParam.getQueueName()) ) {
            throw new RuntimeException("queue name empty");
        }

        if ( StringUtils.isEmpty(listenParam.getRoutingKey()) ) {
            throw new RuntimeException("routing key empty");
        }

        if ( StringUtils.isEmpty(listenParam.getTopicExchange()) ) {
            throw new RuntimeException("exchange empty");
        }

        if (listenConcurrentConsumers < 1){
            throw new RuntimeException("should be gt 0");
        }

        return doInitBundle();
    }

    private MqTopicQueueListenBundle doInitBundle() {
        MqTopicQueueListenBundle bundle = new MqTopicQueueListenBundle();
        bundle.setConnectionFactory(this.connectionFactory);
        RabbitAdmin rabbitAdmin = new RabbitAdmin(this.connectionFactory);
        bundle.setRabbitAdmin(rabbitAdmin);

        MqListenParam listenParam = this.getListenParam();
        Queue queue = new Queue(listenParam.getQueueName());
        rabbitAdmin.declareQueue(queue);
        bundle.setQueue(queue);

        TopicExchange topicExchange = new TopicExchange(listenParam.getTopicExchange(), true, false);
        rabbitAdmin.declareExchange(topicExchange);
        bundle.setTopicExchange(topicExchange);

        Binding binding = BindingBuilder.bind(queue).to(topicExchange).with(listenParam.getRoutingKey());
        rabbitAdmin.declareBinding(binding);
        bundle.setBinding(binding);

        SimpleMessageListenerContainer listenerContainer = new SimpleMessageListenerContainer();
        listenerContainer.setConnectionFactory(connectionFactory);
        listenerContainer.setConcurrentConsumers(this.listenConcurrentConsumers);
        listenerContainer.setChannelTransacted(true);
        listenerContainer.setQueues(queue);

        listenerContainer.setMessageListener(this.messageListener);
        bundle.setListenerContainer(listenerContainer);
        // TODO 放入Context

        return bundle;
    }

    public MqListenParam getListenParam() {
        return listenParam;
    }

    public void setListenParam(MqListenParam listenParam) {
        this.listenParam = listenParam;
    }

    public int getListenConcurrentConsumers() {
        return listenConcurrentConsumers;
    }

    public void setListenConcurrentConsumers(int listenConcurrentConsumers) {
        this.listenConcurrentConsumers = listenConcurrentConsumers;
    }

    public MessageListener getMessageListener() {
        return messageListener;
    }

    public void setMessageListener(MessageListener messageListener) {
        this.messageListener = messageListener;
    }

    public static class MqTopicQueueListenBundle {
        private AbstractMessageListenerContainer listenerContainer;
        private RabbitAdmin rabbitAdmin;
        private ConnectionFactory connectionFactory;
        private TopicExchange topicExchange;
        private Queue queue;
        private Binding binding;

        public AbstractMessageListenerContainer getListenerContainer() {
            return listenerContainer;
        }

        public void setListenerContainer(AbstractMessageListenerContainer listenerContainer) {
            this.listenerContainer = listenerContainer;
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

        public Queue getQueue() {
            return queue;
        }

        public void setQueue(Queue queue) {
            this.queue = queue;
        }

        public Binding getBinding() {
            return binding;
        }

        public void setBinding(Binding binding) {
            this.binding = binding;
        }
    }
}

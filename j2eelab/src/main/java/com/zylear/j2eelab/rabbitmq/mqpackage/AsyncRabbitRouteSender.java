package com.zylear.j2eelab.rabbitmq.mqpackage;

import com.zylear.j2eelab.rabbitmq.bean.RabbitMsgEntityRouteWarpper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.util.StringUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;


public class AsyncRabbitRouteSender {

    private static final Logger logger = LoggerFactory.getLogger(AsyncRabbitRouteSender.class);

    private final LinkedBlockingQueue<RabbitMsgEntityRouteWarpper> iq = new LinkedBlockingQueue<RabbitMsgEntityRouteWarpper>(10000);
    private ExecutorService service;
    private static final int DEFAULT_THREAD_COUNT = 1;

    private Integer threadCount = DEFAULT_THREAD_COUNT;
    private AmqpTemplate amqpTemplate;
    private String senderName = "";

    public void start() {

        stop();
        service = Executors.newFixedThreadPool(threadCount);
        for( int j = 0; j < threadCount; j++ ) {
            final int finalJ = j;
            service.execute(new Runnable() {
                @Override
                public void run() {
                    logger.info("async message thread has started, senderName:{}-{}", senderName, finalJ);

                    while( true ) {
                        RabbitMsgEntityRouteWarpper wrapper = null;
                        try {
                            wrapper = iq.take();
                        } catch (InterruptedException e) {
                            logger.info("async message thread stop");
                            break;
                        }

                        if( wrapper == null ) {
                            continue;
                        }

                        convertAndSenderMessage(wrapper);
                    }
                }
            });
        }

    }

    // send message
    private void convertAndSenderMessage(RabbitMsgEntityRouteWarpper wrapper){
        try {
            if( logger.isDebugEnabled() ) {
//                logger.debug("send msg: {}", JsonUtil.getJsonFromObject(wrapper));
            }
            if( StringUtils.isEmpty(wrapper.getRouteKey()) ) {
                amqpTemplate.convertAndSend(wrapper.getEntity());
            } else {
                amqpTemplate.convertAndSend(wrapper.getRouteKey(), wrapper.getEntity());
            }

        } catch (RuntimeException e) {
//            logger.error("send msg error. msg:{}", JsonUtil.getJsonFromObject(wrapper), e);
        }
    }

    public void stop() {
        if(service != null){
            logger.info("try to stop AsyncRabbitSender.");
            service.shutdownNow();
            logger.info("stop AsyncRabbitSender suc.");
        }
    }

    public void sendMsg(RabbitMsgEntityRouteWarpper msg) {
        try {
            sendMsgToQueue(msg);
        } catch (RuntimeException ignore) {
//            logger.error("send program status msg to queue error. msg:{}", JsonUtil.getJsonFromObject(msg), ignore);
        }
    }

    private void sendMsgToQueue(RabbitMsgEntityRouteWarpper msg) {
        if(!iq.offer(msg)) {
//            logger.error("program status msg queue is full. sender message sync :{}", JsonUtil.getJsonFromObject(msg));
            convertAndSenderMessage(msg);
        } else if( logger.isTraceEnabled() ){
//            logger.trace("program status msg pushed into queue suc. msg:{}", JsonUtil.getJsonFromObject(msg));
        }
    }

    public void setThreadCount(Integer threadCount) {
        this.threadCount = threadCount;
    }

    public void setAmqpTemplate(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }
}

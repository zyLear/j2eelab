package com.zylear.j2eelab.rabbitmq.first;




import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by xiezongyu on 2017/10/10.
 */
public class FirstSender {
    private final static String QUEUE_NAME = "first_queue";
    private final static String EXCHANGE_NAME = "first_topic_exchange";

    public static void main(String[] args) {
        send();
    }

    public static void send()
    {
        ConnectionFactory factory = null;
        Connection connection = null;
        Channel channel = null;
        try {
            factory = new ConnectionFactory();
            factory.setHost("localhost");
//            factory.setVirtualHost("/");
//            factory.setPort(5672);
//            factory.setUsername("guest");
//            factory.setPassword("guest");
            connection = factory.newConnection();
            channel = connection.createChannel();
//            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "first_message  2";
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);

            //第二个参数是routing_key
            channel.basicPublish(EXCHANGE_NAME, "one.two", null, message.getBytes("UTF-8"));
            System.out.println("已经发送消息....."+message);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } finally{
            try {
                //关闭资源
                channel.close();
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        }
    }
}

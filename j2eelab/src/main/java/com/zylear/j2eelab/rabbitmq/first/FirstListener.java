package com.zylear.j2eelab.rabbitmq.first;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by xiezongyu on 2017/10/10.
 */
public class FirstListener {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) {
        receive();
    }

    public static void receive() {
        ConnectionFactory factory = null;
        Connection connection = null;
        Channel channel = null;

        try {
            factory = new ConnectionFactory();
            factory.setHost("localhost");
//            factory.setVirtualHost("/");
            connection = factory.newConnection();

            channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            Consumer consumer = new DefaultConsumer(channel) {

                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                                           byte[] body) throws IOException {
                    System.out.println("11111111111");
                    String message = new String(body, "UTF-8");
                    System.out.println("收到消息....." + message);
                }
            };

            channel.basicConsume(QUEUE_NAME, true, consumer);
            Thread.sleep(10000);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                Thread.sleep(5000);
                //关闭资源
                channel.close();
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

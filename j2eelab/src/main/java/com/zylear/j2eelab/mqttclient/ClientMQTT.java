package com.zylear.j2eelab.mqttclient;

import java.util.concurrent.ScheduledExecutorService;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class ClientMQTT {

    public static final String HOST = "tcp://172.22.13.144:19090";
//    public static final String HOST = "tcp://localhost:19090";
    public static final String TOPIC = "topic";
    private static final String clientid = "1";
    private MqttClient client;
    private MqttConnectOptions options;
    //        private String userName = "wxid_52ejw4hw2zsr22";
//    private String userName = "wxid_bsmfbgxk1rea31";
    public static  String userName = "wxid_plp93p8ipfaa22";

    private String passWord = "password";

    private ScheduledExecutorService scheduler;

    private void start() {
        try {
            // host为主机名，clientid即连接MQTT的客户端ID，一般以唯一标识符表示，MemoryPersistence设置clientid的保存形式，默认为以内存保存
            client = new MqttClient(HOST, clientid, new MemoryPersistence());
            // MQTT的连接设置
            options = new MqttConnectOptions();
            // 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接
            options.setCleanSession(true);
            // 设置连接的用户名
            options.setUserName(userName);
//            // 设置连接的密码
            options.setPassword(passWord.toCharArray());
            // 设置超时时间 单位为秒
            options.setConnectionTimeout(1000);
//             设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
            options.setKeepAliveInterval(30);
            // 设置回调
            client.setCallback(new PushCallback());
//            MqttTopic topic = client.getTopic(TOPIC);
            //setWill方法，如果项目中需要知道客户端是否掉线可以调用该方法。设置最终端口的通知消息
//            options.setWill(topic, "close".getBytes(), 2, true);

            client.connect(options);
//            client.connect();
            //订阅消息
//            int[] Qos = {1};
//            String[] topic1 = {TOPIC};
//            client.subscribe(topic1, Qos);
//            client.subscribe(topic1);
//            Thread.sleep(3000);


            MqttMessage mqttMessage = new MqttMessage();
            mqttMessage.setRetained(true);
            mqttMessage.setQos(1);
            mqttMessage.setId(3);
            mqttMessage.setPayload(("{\"msgType\":\"wx_login\",\"wxId\":\"" + userName + "\"}").getBytes());
            client.publish("topic", mqttMessage);
//            client.publish("topic", mqttMessage);
//            client.disconnect();

//

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws MqttException {
        ClientMQTT client = new ClientMQTT();
        client.start();

//        System.out.println("{\"msgType\":\"wx_login\",\"wxId\":\"" + userName + "\"}");

    }
}


class PushCallback implements MqttCallback {

    public void connectionLost(Throwable cause) {
        // 连接丢失后，一般在这里面进行重连
        System.out.println("连接断开，可以做重连");
    }

    public void deliveryComplete(IMqttDeliveryToken token) {
        System.out.println("deliveryComplete---------" + token.isComplete());
    }


    public void messageArrived(String topic, MqttMessage message) throws Exception {
        // subscribe后得到的消息会执行到这里面
        System.out.println("接收消息主题 : " + topic);
        System.out.println("接收消息Qos : " + message.getQos());
        System.out.println("接收消息内容 : " + new String(message.getPayload()));
    }


}
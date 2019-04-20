package com.itqf.demo02;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * 消息的监听器,异步接收消息，客户端
 * 监听到消息，自动执行onMessage方法
 *
 *
 * 消息的同步接收是指：客户端主动去接收消息，客户端课采用MessageConsume的receive方法去接收下一个消息。
 *
 * 消息的异步接收是指：当消息到达MQ服务器时，MQ服务器主动通知客户端，
 * 客户点通过注册一个实现MessageListener接口的对象到MessageConsumer。
 * MessageListener只有一个必须实现的方法：onMessage,它只接受一个参数Message。
 * 在为每个发送到Destination的消息实现onMessage时，调用该方法。
 */
public class MyMessageListenr implements MessageListener {

    @Override
    public void onMessage(Message message) {

        try {
            if (message instanceof MapMessage){
                MapMessage message1 = (MapMessage) message;
                System.out.println(message1.getInt("id"));
                System.out.println(message1.getString("name"));
                System.out.println(message1.getStringProperty("sex"));
                System.out.println(message1.getIntProperty("age"));
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}

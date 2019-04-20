package com.itqf.demo03;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 订阅者
 */
public class Subscriber {

    private static String brokerURL = "tcp://192.168.43.50:61616";
    private static String username = ActiveMQConnectionFactory.DEFAULT_USER;
    private static String password = ActiveMQConnectionFactory.DEFAULT_PASSWORD;


    public static void main(String[] args) {
        ConnectionFactory connectionFactory = null;
        Connection connection = null;
        Session session = null;
        Destination destination = null;
        MessageConsumer messageConsumer = null;
        try {
            //1 创建connectionFactory
            connectionFactory = new ActiveMQConnectionFactory(username, password, brokerURL);
            //2，Connection对象
            connection = connectionFactory.createConnection();
            //开始启动
            connection.start();
            //4,创建session对象
            session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            //5,创建消息源对象
            destination = session.createTopic("myTopic");
            //6，创建消息的订阅者
            messageConsumer = session.createConsumer(destination);
            //7，接收消息
            //同步
            // TextMessage message = (TextMessage) messageConsumer.receive();
            // System.out.println(message.getText());

            //异步接收
            messageConsumer.setMessageListener(new MyMessageListener());
            session.commit();

        } catch (
                JMSException e) {
            e.printStackTrace();
        }
    }
}

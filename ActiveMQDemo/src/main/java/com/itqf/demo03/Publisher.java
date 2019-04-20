package com.itqf.demo03;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Publisher {

    private static String brokerURL="tcp://192.168.43.50:61616";
    private static String username=ActiveMQConnectionFactory.DEFAULT_USER;
    private  static String password=ActiveMQConnectionFactory.DEFAULT_PASSWORD;


    public static void main(String[] args) {
        ConnectionFactory connectionFactory = null;
        Connection connection = null;
        Session session = null;
        Destination destination = null;
        MessageProducer messageProducer;
        try {
            //1 创建connectionFactory
            connectionFactory = new ActiveMQConnectionFactory(username,password,brokerURL);
            //2，Connection对象
            connection = connectionFactory.createConnection();
            //开始启动
            connection.start();
            //4,创建session对象
            session = connection.createSession(true,Session.AUTO_ACKNOWLEDGE);
            //5,创建消息源对象
            destination  = session.createTopic("myTopic" );
            //6,创建生产者
            messageProducer = session.createProducer(destination);
            //7,发布消息
            TextMessage message = session.createTextMessage();
            message.setText("hello,发布订阅模式!!!");//对象-->json
            messageProducer.send(message);
            session.commit();

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}

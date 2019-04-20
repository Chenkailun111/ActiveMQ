package com.itqf.demo01;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * ConnectionFactory
 * Connection
 * Session
 * Destination
 * MessageProducer
 * MessageConsumer
 */

public class Sender {

    private static String brokerURL="tcp://192.168.43.50:61616";
    /*private static String username=ActiveMQConnectionFactory.DEFAULT_USER;
    private  static String password=ActiveMQConnectionFactory.DEFAULT_PASSWORD;*/

    private static String username = "admin";
    private static String password = "admin";

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = null;
        Connection connection = null;
        Session session = null;
        Destination destination = null;
        MessageProducer messageProducer;

        try {
            //1,创建ConnectionFactory
            //connectionFactory = new ActiveMQConnectionFactory(brokerURL);
            connectionFactory = new ActiveMQConnectionFactory(username,password,brokerURL);
            //2,创建Connection对象
            connection = connectionFactory.createConnection();
            //3,开始连接
            connection.start();
            //4,创建Session对象
            session = connection.createSession(true,Session.AUTO_ACKNOWLEDGE);
            //5,创建Destination对象
            destination = session.createQueue("myQueue");
            //创建消息的提供者对象（生产者）
            messageProducer = session.createProducer(destination);
            //6,发送出去
            for (int i = 0; i < 10; i++) {
                TextMessage message = session.createTextMessage();
                message.setText("helloworld！！"+i);

                messageProducer.send(message);
            }
            session.commit();

        } catch (JMSException e) {
            e.printStackTrace();
        }finally {
            try {
                session.close();
                connection.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}

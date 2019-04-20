package com.itqf.demo02;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Receiver {
    private static String brokerURL = "tcp://192.168.43.50:61616";
    private static String username = ActiveMQConnectionFactory.DEFAULT_USER;
    private static String password = ActiveMQConnectionFactory.DEFAULT_PASSWORD;


    public static void main(String[] args) {
        ConnectionFactory connectionFactory = null;
        Connection connection = null;
        Session session = null;
        Destination destination = null;
        MessageConsumer messageConsumer;

        try {
            //1,创建ConnectionFactory
            connectionFactory = new ActiveMQConnectionFactory(username, password, brokerURL);
            //2,创建Connection对象
            connection = connectionFactory.createConnection();
            //3,启动
            connection.start();
            //4,创建session对象
            session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            //5,创建Destination
            destination = session.createQueue("myQueue1");
            //消息的过滤，只能过滤setXXXProperty()封装的数据
            String selector_01 = "age >= 15";
            String selector_02 = "sex='男'";
            //6,创建消息消费者
            messageConsumer = session.createConsumer(destination, selector_02);

            // messageConsumer.receive();//同步接收方式  注意：服务需要一直开启
            messageConsumer.setMessageListener(new MyMessageListenr());

        } catch (Exception e) {
        }
    }
}

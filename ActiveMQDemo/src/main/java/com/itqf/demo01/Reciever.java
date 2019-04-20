package com.itqf.demo01;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 主题模式，点对点
 */
public class Reciever {
    private static String brokerURL="tcp://192.168.43.50:61616";
    private static String username=ActiveMQConnectionFactory.DEFAULT_USER; //系统默认的是admin
    private  static String password=ActiveMQConnectionFactory.DEFAULT_PASSWORD;


    public static void main(String[] args) {
        ConnectionFactory connectionFactory = null;
        Connection connection = null;
        Session session = null;
        Destination destination = null;
        MessageConsumer messageConsumer;//接收消息

        try {
            //1,创建ConnectionFactory
            connectionFactory = new ActiveMQConnectionFactory(username,password,brokerURL);
            //2,创建Connection对象
            connection = connectionFactory.createConnection();
            //3,启动
            connection.start();
            //4,创建session对象
            session = connection.createSession(true,Session.AUTO_ACKNOWLEDGE);
            //5,创建Destination
             destination = session.createQueue("myQueue");
            //6,创建消息消费者
            messageConsumer = session.createConsumer(destination);
            while(true){
              TextMessage message  =(TextMessage) messageConsumer.receive();
              if (message==null){
                  break;
              }
                System.out.println(message.getText());
            }

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}

package com.itqf.demo02;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 点对点模式的生产者
 */
public class Sender {

    private static String brokerURL = "tcp://192.168.43.50:61616";
    private static String username = ActiveMQConnectionFactory.DEFAULT_USER;
    private static String password = ActiveMQConnectionFactory.DEFAULT_PASSWORD;


    public static void main(String[] args) {
        ConnectionFactory connectionFactory = null;
        Connection connection = null;
        Session session = null;
        Destination destination = null;
        MessageProducer messageProducer;


        try {
            //1,创建ConnectionFactory
            //connectionFactory = new ActiveMQConnectionFactory(brokerURL);
            connectionFactory = new ActiveMQConnectionFactory(username, password, brokerURL);
            //2,创建Connection对象
            connection = connectionFactory.createConnection();
            //3,开始连接
            connection.start();
            //4,创建Session对象
            session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            //5,创建Destination对象
            destination = session.createQueue("myQueue1");
            //创建消息的提供者对象（生产者）
            messageProducer = session.createProducer(destination);

            MapMessage message1 = session.createMapMessage();
            message1.setInt("id", 1);
            message1.setString("name", "jack");
            //只有使用了setXXXproperty()方法设置的消息，才能过滤
            message1.setStringProperty("sex", "男");
            message1.setIntProperty("age", 18);

            MapMessage message2 = session.createMapMessage();
            message2.setInt("id", 2);
            message2.setString("name", "rose");
            //只有使用了setXXXproperty()方法设置的消息，才能过滤
            message2.setStringProperty("sex", "女");
            message2.setIntProperty("age", 20);

            MapMessage message3 = session.createMapMessage();
            message3.setInt("id", 3);
            message3.setString("name", "lucy");
            //只有使用了setXXXproperty()方法设置的消息，才能过滤
            message3.setStringProperty("sex", "女");
            message3.setIntProperty("age", 12);

            //发送消息
            /**
             * send(Destination destination,Message message,int deliveryMode,int priority,long timeToLive);
             * 参数说明：
             * 1、destination：通过session创建Destination对象，指的是一个客户端用来指定生产的消息目标或消息来源的对象。在PTP模式中，Destination被称作Queue队列，在Pub/Sub模式中Destination被称作topic主题。在程序中可以使用多个Queue或topic
             * 2、message：消息
             * 3、deliveryMode:传送模式，PERSISTENT（默认）和NON_PERSISTENT，
             * 如果容忍消息丢失，可以使用NON_PERSISTENT。
             * 4、priority：消息优先级，从0-9十个级别，0-4是普通消息，5-9是加急消息，默认是4。
             * 5、timeToLive：消息过期时间，默认情况下消息永不过期。
             */
            messageProducer.send(destination, message1, DeliveryMode.NON_PERSISTENT, 1, 60 * 1000 * 30);
            messageProducer.send(destination, message2, DeliveryMode.NON_PERSISTENT, 1, 60 * 1000 * 30);
            messageProducer.send(destination, message3, DeliveryMode.NON_PERSISTENT, 1, 60 * 1000 * 30);
            session.commit();

        } catch (Exception e) {

            e.printStackTrace();
        } finally {
            try {
                if (session != null) {
                    session.close();
                }
            } catch (JMSException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (JMSException e) {
                e.printStackTrace();
            }

        }
    }


}

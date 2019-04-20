package com.itqf.demo03;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class MyMessageListener  implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage){
                TextMessage textMessage =(TextMessage) message;
                System.out.println(textMessage.getText());
            }
            //进行逻辑处理

        } catch (JMSException e) {
            e.printStackTrace();
        }

    }
}

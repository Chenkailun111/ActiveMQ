package com.itqf.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.xml.soap.Text;

public class MyMessageListener  implements MessageListener {

    public void onMessage(Message message) {

        if (message instanceof TextMessage){
            try {
                TextMessage textMessage = (TextMessage) message;
                System.out.println(textMessage.getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
            //进行业务逻辑的处理
        }

    }
}

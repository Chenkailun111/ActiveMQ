package test;

import com.itqf.service.SendMessage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSender {

    public static void main(String[] args) {
        ApplicationContext a = new ClassPathXmlApplicationContext("applicationContext-activemq.xml");
        SendMessage sendMessage = a.getBean(SendMessage.class);

        sendMessage.sendMsg("{id:1,name:娃哈哈,price:100}");


    }
}

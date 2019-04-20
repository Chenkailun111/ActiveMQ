package com.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestConsumer {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext a = new ClassPathXmlApplicationContext("applicationContext-activemq.xml");

        a.start();


    }

}

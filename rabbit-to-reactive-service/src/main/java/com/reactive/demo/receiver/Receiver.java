package com.reactive.demo.receiver;

import com.reactive.demo.HistoryModel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {


    @RabbitListener(queues = "spring-boot")
    public void receiveMessage(HistoryModel message) {
        System.out.println("Received <" + message + ">");
    }


}

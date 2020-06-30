package com.covid19.updateservice.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

/**
 * This class is not neccesary
 */
@Component
public class MessageConsumer {
    private String stompTopic = "/topic/updateData";
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @KafkaListener(topics = "${kafka.output.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeMessage(String msg) {
        System.out.println("Message received: " + msg.substring(0,25));
//        System.out.println("Message received: " + msg);
        messagingTemplate.convertAndSend(stompTopic, msg);
    }
}

package com.covid19.updateservice.kafka;

import com.covid19.updateservice.service.PollingService;
import com.covid19.updateservice.kafka.KafkaStreamsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {

    @Value("${kafka.input.topic}")
    private String kafkaInputMapData;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private  PollingService pollingService;

    @Scheduled(fixedRate = 30000)
    public void produce() {
        String msg = pollingService.outputMapData();
        System.out.println("**producer*****************************");
//        System.out.println("**producer json **" +  msg);
        kafkaTemplate.send(kafkaInputMapData, msg);
        System.out.println("** message producer enviado");
    }

}

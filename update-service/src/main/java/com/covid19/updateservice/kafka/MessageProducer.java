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

    private String count= "fistAccess";

    @Scheduled(cron = "0/30 * * * * ?")
    public void initProduce() {
        String msg = pollingService.outputMapData();
        System.out.println("****************************** count" + count);
        if( (!msg.equals(null)  || !msg .equals("undefined") ) && (count.equals("fistAccess"))) {
            kafkaTemplate.send(kafkaInputMapData, msg);
            System.out.println("mi primera vez");
            count = "accessed";
        }
        if (msg.equals(null)  || msg .equals("undefined") )  {
            kafkaTemplate.send(kafkaInputMapData, "[{}]");
            System.out.println("error");
        }
    }

    @Scheduled(cron = "* * 0/12 * * ?")
    public void produce() {
        String msg = pollingService.outputMapData();
        if( !msg.equals(null)  || !msg .equals("undefined"))
            kafkaTemplate.send(kafkaInputMapData, msg);
    }

}

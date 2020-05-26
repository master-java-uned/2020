package com.covid19.updateservice.kafka;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;
@EnableKafka
@Configuration
public class KafkaTopicConfig {
    @Value("${kafka.input.topic}")
    private String kafkaInputMapData;

    @Value("${kafka.output.topic}")
    private String kafkaOutputMapData;


    @Bean
    public NewTopic inputTopic() {
        return new NewTopic(kafkaInputMapData, 1, (short) 1);
    }

    @Bean
    public NewTopic outputTopic() {
        return new NewTopic(kafkaOutputMapData, 1, (short) 1);
    }
}

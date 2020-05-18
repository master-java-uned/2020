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

    @Value("${spring.kafka.consumer.bootstrap-servers}")
    private String kafkaBootstrapServer;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG,kafkaBootstrapServer);
        configs.put(AdminClientConfig.SEND_BUFFER_CONFIG,100000000);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic inputTopic() {
       return TopicBuilder.name(kafkaInputMapData)
                .partitions(1)
                .replicas(1)
                .config(TopicConfig.COMPRESSION_TYPE_CONFIG, "zstd")
                .build();
    }

    @Bean
    public NewTopic outputTopic() {
        return new NewTopic(kafkaOutputMapData, 1, (short) 1);
    }
}

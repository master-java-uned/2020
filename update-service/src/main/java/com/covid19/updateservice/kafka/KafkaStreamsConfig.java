package com.covid19.updateservice.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.connect.json.JsonSerializer;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@EnableKafka
@Configuration
public class KafkaStreamsConfig {
    @Value("${kafka.input.topic}")
    private String kafkaInputMapData;

    @Value("${kafka.output.topic}")
    private String kafkaOutputMapData;

    @Value("${spring.kafka.consumer.bootstrap-servers}")
    private String kafkaBootstrapServer;

    @Autowired
    private KafkaProperties kafkaProperties;

    @Bean
    public KStream<String, String> kstream() {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "update-service");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBootstrapServer);
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put("max.request.size",100000000 );
        StreamsBuilder streamsBuilder = new StreamsBuilder();

        final KStream<String, String> stream = streamsBuilder.stream(kafkaInputMapData,
                Consumed.with(Serdes.String(), Serdes.String()));

        stream.map((key, value) -> KeyValue.pair(key, value)).to(kafkaOutputMapData,
                Produced.with(Serdes.String(), Serdes.String()));

        KafkaStreams streams = new KafkaStreams(streamsBuilder.build(), props);
        streams.start();

        return stream;
    }




}

package com.epam.demo.configuration;

import com.epam.demo.model.Commit;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Value("${kafka.host}")
    private String kafkaHost;

    @Value("${kafka.port}")
    private String kafkaPort;

    @Bean
    public ConsumerFactory<String, Commit> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                kafkaHost + ":" + kafkaPort);
        props.put(
                ConsumerConfig.GROUP_ID_CONFIG,
                UUID.randomUUID().toString());
        props.put(
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
        props.put(
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                JsonDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(props, null, new JsonDeserializer<>(Commit.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Commit>
    kafkaListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<String, Commit> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}

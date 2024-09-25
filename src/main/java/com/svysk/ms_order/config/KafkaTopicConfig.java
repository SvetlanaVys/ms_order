package com.svysk.ms_order.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;

@Configuration
@EnableKafka
public class KafkaTopicConfig {

    public static final String ORDER_REQUEST_TOPIC = "order-request";

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        var configs = new HashMap<String, Object>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, this.bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic orderRequestTopic() {
        return new NewTopic(ORDER_REQUEST_TOPIC, 1, (short) 1);
    }

}

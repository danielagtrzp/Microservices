package com.microservices.order.configurations;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.List;
import java.util.Map;

@Configuration
public class TopicConfiguration {

    @Bean
    public List<NewTopic> createTopics() {

        return List.of(
                TopicBuilder.name("notification-order-ready")
                            .partitions(3)
                            .replicas(3)
                            .configs(Map.of("min.insync.replicas", "2"))
                            .build()
        );

    }
}
package com.example.paymentservice.config;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@RequiredArgsConstructor
public class KafkaTopic{
    @Bean
    public NewTopic smsTopic(){
        return TopicBuilder.name("Email").partitions(2).replicas(1).build();
    }
}

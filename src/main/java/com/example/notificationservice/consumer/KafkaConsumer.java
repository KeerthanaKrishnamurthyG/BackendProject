package com.example.notificationservice.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;

import org.springframework.stereotype.Service;


@Service
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = "Email", groupId = "myGroup")
    public void consume(String message) {
        log.info(String.format("$$ -> Consumed Message from consumer groupId-> myGroup ->  %s", message));


    }
}



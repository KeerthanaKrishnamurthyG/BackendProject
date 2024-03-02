package com.example.paymentservice.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j

public class KafkaProducer {
    private KafkaTemplate<String,String> kafkaTemplate;
    public KafkaProducer(KafkaTemplate kafkaTemplate){
        this.kafkaTemplate=kafkaTemplate;
    }
    public void sendMessage(String message) {

        log.info("from Publisher : " + message);
        kafkaTemplate.send("Email", message);

    }

}

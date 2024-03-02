package com.example.paymentservice.controller;

import com.example.paymentservice.producer.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/producer")

public class ProducerController {
    private KafkaProducer kafkaProducer;
    public ProducerController(KafkaProducer kafkaProducer){
        this.kafkaProducer=kafkaProducer;
    }


    @PostMapping("/message")
        public ResponseEntity<String> accessProducers(@RequestBody String message){
        kafkaProducer.sendMessage(message);
        return ResponseEntity.ok("message reached producer");
    }

}

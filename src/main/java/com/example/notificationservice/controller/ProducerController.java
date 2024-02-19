package com.example.notificationservice.controller;

import com.example.notificationservice.producer.KafkaProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

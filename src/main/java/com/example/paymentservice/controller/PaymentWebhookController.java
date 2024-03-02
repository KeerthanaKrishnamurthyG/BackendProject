package com.example.paymentservice.controller;

import jdk.jfr.Event;
import lombok.Getter;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stripeWebhook")
public class PaymentWebhookController {
    @PostMapping("/status")
    public void  stripeWebhook(@RequestBody  Event event){
        System.out.println("from stripe");


    }

}

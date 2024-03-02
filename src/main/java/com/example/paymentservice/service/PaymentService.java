package com.example.paymentservice.service;

import com.example.paymentservice.paymentgateway.PaymentGateway;

import com.example.paymentservice.producer.KafkaProducer;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private PaymentGateway paymentGateway;
    private final KafkaProducer kafkaProducer;
    public PaymentService(PaymentGateway paymentGateway, KafkaProducer kafkaProducer){
        this.paymentGateway=paymentGateway;

        this.kafkaProducer = kafkaProducer;
    }
    public String createPayment(String orderId, Long amount, String phoneNumber,String emailId) throws StripeException, RazorpayException {
        String transactionId=paymentGateway.generatePaymentLink(orderId,amount,phoneNumber,emailId);
        if (transactionId.equals(null)){
            kafkaProducer.sendMessage("Payment not Completed");

        }
        else{
            kafkaProducer.sendMessage("Payment Processed Success from payment service");
        }
        return transactionId;
    }
}

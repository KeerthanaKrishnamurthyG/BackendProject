package com.example.paymentservice.controller;

import com.example.paymentservice.dto.InitiatePaymentRequestDto;
import com.example.paymentservice.dto.InitiatePaymentResponseDto;
import com.example.paymentservice.service.PaymentService;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private PaymentService paymentService;
    public PaymentController(PaymentService paymentService){
        this.paymentService=paymentService;
    }


    @PostMapping("/generatePayment")
    public String initatePayment(@RequestBody InitiatePaymentRequestDto initiatePaymentRequestDto) throws StripeException, RazorpayException {
        return paymentService.createPayment(initiatePaymentRequestDto.getOrderId(),
                initiatePaymentRequestDto.getAmount(),
                initiatePaymentRequestDto.getPhoneNumber(),
                initiatePaymentRequestDto.getEmailId());




    }
    @PostMapping("/{demo}")
    public String demo(@PathVariable("demo") String demo1){
        return demo1;

    }
}

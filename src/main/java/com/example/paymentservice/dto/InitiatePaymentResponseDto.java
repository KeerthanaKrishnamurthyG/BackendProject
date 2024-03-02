package com.example.paymentservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InitiatePaymentResponseDto {
    private  String orderId;
    private Long amount;
    private String emailId;
    private String phoneNumber;
}

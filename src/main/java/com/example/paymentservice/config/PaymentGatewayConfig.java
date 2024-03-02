package com.example.paymentservice.config;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentGatewayConfig {
    @Value("${RAZORPAY_KEY_ID}")
    private String razorPayId;
    @Value("${RAZORPAY_KEY_SECRET}")
    private String razorPaySecret;


    @Bean
    public RazorpayClient getRazorPayClient() throws RazorpayException {
        return new RazorpayClient(razorPayId,razorPaySecret);
    }
}

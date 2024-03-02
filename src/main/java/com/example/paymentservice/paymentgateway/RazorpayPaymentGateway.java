package com.example.paymentservice.paymentgateway;

//import com.example.paymentservice.consumer.KafkaConsumer;
import com.razorpay.PaymentLink;
import org.json.JSONObject;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service

public class RazorpayPaymentGateway implements PaymentGateway {
    private RazorpayClient razorpayClient;
    public RazorpayPaymentGateway(RazorpayClient razorpayClient){
        this.razorpayClient=razorpayClient;
    }

    @Override
    public String generatePaymentLink(String orderId, Long amount, String phoneNumber, String email) throws RazorpayException {
//        RazorpayClient razorpay = new RazorpayClient("[YOUR_KEY_ID]", "[YOUR_KEY_SECRET]");


        JSONObject paymentLinkRequest = new JSONObject();
        paymentLinkRequest.put("amount",amount);
        paymentLinkRequest.put("currency","INR");
        paymentLinkRequest.put("accept_partial",false);
//        paymentLinkRequest.put("first_min_partial_amount",100);
//        epoch time generate
        paymentLinkRequest.put("expire_by",1707060611);
        paymentLinkRequest.put("reference_id","TS1989");
        paymentLinkRequest.put("description","Payment for policy no #23456");
        JSONObject customer = new JSONObject();
        customer.put("name",phoneNumber);
        customer.put("contact","keerthana");
        customer.put("email",email);
        paymentLinkRequest.put("customer",customer);
        JSONObject notify = new JSONObject();
        notify.put("sms",true);
        notify.put("email",true);
        paymentLinkRequest.put("notify",notify);
        paymentLinkRequest.put("reminder_enable",true);
        JSONObject notes = new JSONObject();
        notes.put("policy_name","Jeevan Bima");
        paymentLinkRequest.put("notes",notes);
        paymentLinkRequest.put("callback_url","https://example-callback-url.com/");
        paymentLinkRequest.put("callback_method","get");


        PaymentLink payment = razorpayClient.paymentLink.create(paymentLinkRequest);

        return payment.toString();

    }
}
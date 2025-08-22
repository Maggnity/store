package com.codewithmaggnity.store.service;

import org.springframework.stereotype.Component;

import com.codewithmaggnity.store.service.Payment.PaymentService;

@Component
public class OrderService {

    private PaymentService paymentService;

    public void placeOrder() {
        paymentService.processPayment(10);
    }

    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
}

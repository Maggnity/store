package com.codewithmaggnity.store;

public class StripePaymentService implements PaymentService {
    public void processPayment(double amount) {

        System.out.println("Stripe");
        System.out.println("Amount: " + amount + " paid.");

    }
}

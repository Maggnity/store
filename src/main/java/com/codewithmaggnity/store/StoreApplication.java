package com.codewithmaggnity.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.codewithmaggnity.store.service.OrderService;
import com.codewithmaggnity.store.service.Payment.PaypalPaymentService;

@SpringBootApplication
public class StoreApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(StoreApplication.class, args);

		var orderService = context.getBean(OrderService.class);

		orderService.setPaymentService(new PaypalPaymentService());
		orderService.placeOrder();
	}
}

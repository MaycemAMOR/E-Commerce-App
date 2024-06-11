package com.maytech.ecommerce.kafka.order;

import com.maytech.ecommerce.kafka.payment.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

// make sure tha we have the same attributes like at  OrderConfirmation at order microservice project

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        Customer customer,
        List<Product> products
) {
}

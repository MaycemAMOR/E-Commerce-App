package com.maytech.ecommerce.kafka.payment;

import java.math.BigDecimal;

// make sure tha we have the same attributes like at  PaymentNotificationRequest at Payment microservice project
public record PaymentConfirmation(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerFirstName,
        String customerLastName,
        String customerEmail
) {
}

package com.maytech.ecommerce.kafka;

import com.maytech.ecommerce.entity.PaymentMethod;

import java.math.BigDecimal;

public record PaymentNotificationRequest(
        String OrderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerFirstName,
        String customerLastName,
        String CustomerEmail
) {
}

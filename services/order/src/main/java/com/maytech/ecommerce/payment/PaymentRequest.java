package com.maytech.ecommerce.payment;

import com.maytech.ecommerce.customer.CustomerResponse;
import com.maytech.ecommerce.entity.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethode,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}

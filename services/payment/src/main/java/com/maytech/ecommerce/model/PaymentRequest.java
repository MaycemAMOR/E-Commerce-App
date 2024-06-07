package com.maytech.ecommerce.model;

import com.maytech.ecommerce.entity.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        Integer id,
        BigDecimal amount,
        PaymentMethod paymentMethode,
        Integer orderId,
        String orderReference,
        Customer customer
) {


}

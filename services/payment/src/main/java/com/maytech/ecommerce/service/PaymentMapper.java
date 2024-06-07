package com.maytech.ecommerce.service;

import com.maytech.ecommerce.entity.Payment;
import com.maytech.ecommerce.model.PaymentRequest;
import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {
    public Payment toPayment(PaymentRequest request) {
        return Payment.builder()
                .paymentMethode(request.paymentMethode())
                .id(request.id())
                .amount(request.amount())
                .orderId(request.orderId())
                .build();
    }
}

package com.maytech.ecommerce.kafka;

import com.maytech.ecommerce.customer.CustomerResponse;
import com.maytech.ecommerce.entity.PaymentMethod;
import com.maytech.ecommerce.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}

package com.maytech.ecommerce.model;

public record OrderLineResponse(
        Integer orderId,
        double quantity
) {
}

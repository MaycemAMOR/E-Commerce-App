package com.maytech.ecommerce.service;

import com.maytech.ecommerce.entity.Order;
import com.maytech.ecommerce.entity.OrderLine;
import com.maytech.ecommerce.model.OrderLineRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderLineMapper {
    public OrderLine toOrderLine(OrderLineRequest request) {
        return OrderLine.builder()
                .id(request.id())
                .productId(request.productId())
                .order(
                        Order.builder()
                                .id(request.orderId())
                                .build())
                .quantity(request.quantity())
                .build();
    }
}

package com.maytech.ecommerce.controller;

import com.maytech.ecommerce.model.OrderLineResponse;
import com.maytech.ecommerce.service.OrderLineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
@RequestMapping("/api/v1/order-lines")
public class OrderLineController {
    private final OrderLineService service;

    @GetMapping("/order/{order-id")
    public ResponseEntity<List<OrderLineResponse>> findAllByOrderId(
            @PathVariable("order-id") Integer orderId) {
        return ResponseEntity.ok(service.findAllByOrderId(orderId));
    }

}

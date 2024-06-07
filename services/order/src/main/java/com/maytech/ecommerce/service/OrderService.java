package com.maytech.ecommerce.service;

import com.maytech.ecommerce.customer.CustomerClient;
import com.maytech.ecommerce.exception.BusinessException;
import com.maytech.ecommerce.kafka.OrderConfirmation;
import com.maytech.ecommerce.kafka.OrderProducer;
import com.maytech.ecommerce.model.OrderLineRequest;
import com.maytech.ecommerce.model.OrderRequest;
import com.maytech.ecommerce.model.OrderResponse;
import com.maytech.ecommerce.model.product.PurchaseRequest;
import com.maytech.ecommerce.payment.PaymentClient;
import com.maytech.ecommerce.payment.PaymentRequest;
import com.maytech.ecommerce.product.ProductClient;
import com.maytech.ecommerce.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;

    public Integer createOrder(OrderRequest request) {

        //check the customer --> OpenFeign
        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create order :: No customer exists with the provided ID :: " + request.customerId()));

        //purchase the product --> products-microservice (RestTemplate) we can use OpenFeign also

        var purchaseProducts = this.productClient.purchaseProducts(request.products());

        //persist order
        var order = this.repository.save(mapper.toOrder(request));

        // persist order lines
        for (PurchaseRequest purchaseRequest : request.products()) {
            orderLineService.saveOrderLine(new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }

        // todo start payment process
        var paymentRequest = new PaymentRequest(
                request.amount(),
                request.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer);
        paymentClient.requestOrderPayment(paymentRequest);


        //send the order confirmation --> notification-microservice (kafka)
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchaseProducts
                )
        );

        return order.getId();
    }

    public List<OrderResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponse findById(Integer orderId) {
        return repository.findById(orderId)
                .map(mapper::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException(String.format(" No order found with the provided Id :: %d", orderId)));
    }
}

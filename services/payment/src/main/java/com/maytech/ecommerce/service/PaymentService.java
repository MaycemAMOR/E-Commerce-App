package com.maytech.ecommerce.service;

import com.maytech.ecommerce.kafka.NotificationProducer;
import com.maytech.ecommerce.kafka.PaymentNotificationRequest;
import com.maytech.ecommerce.model.PaymentRequest;
import com.maytech.ecommerce.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository repository;
    private final PaymentMapper mapper;
    private final NotificationProducer notificationProducer;

    public Integer createPayment(PaymentRequest request) {
        //save the payment
        var payment = repository.save(mapper.toPayment(request));
        //send the notification to the kafka broker
        notificationProducer.sendNotification(new PaymentNotificationRequest(
                request.orderReference(),
                request.amount(),
                request.paymentMethode(),
                request.customer().firstname(),
                request.customer().lastname(),
                request.customer().email()
        ));
        return payment.getId();
    }
}

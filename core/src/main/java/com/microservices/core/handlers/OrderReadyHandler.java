package com.microservices.core.handlers;

import com.microservices.core.entities.CreateOrderResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(topics="notification-order-ready")
@Slf4j
public class OrderReadyHandler {

    @KafkaHandler
    public void handle(CreateOrderResponse createOrderResponse) {
        //this can be served an SPA
        log.info("Received a new event: " + createOrderResponse);
    }

}

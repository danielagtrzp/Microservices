package com.microservices.order.controllers;

import com.microservices.order.dtos.CreateOrderResponse;
import com.microservices.order.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/users/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateOrderResponse createOrder(@PathVariable Long userId) {
        return orderService.createOrder(userId);

    }

}

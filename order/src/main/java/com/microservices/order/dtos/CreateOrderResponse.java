package com.microservices.order.dtos;


import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class CreateOrderResponse {
    private Long orderId;
    private List<OrderItemKafka> orderItems = new ArrayList<>();
    private Double totalPrice;
    private Long user;
}

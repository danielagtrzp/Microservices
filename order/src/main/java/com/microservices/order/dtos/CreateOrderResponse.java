package com.microservices.order.dtos;


import com.microservices.order.entities.OrderItem;
import lombok.*;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class CreateOrderResponse {
    private Long orderId;
    private List<OrderItem> orderItems;
    private Double totalPrice;
    private Long user;
}

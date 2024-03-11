package com.microservices.order.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class OrderItemKafka {
    private Long orderItemId;
    private String orderItemName;
    private Double orderItemPrice;
    private Long orderItemQuantity;
}

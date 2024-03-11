package com.microservices.order.dtos;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class OrderCartFeignResponse {
    private String cartItemName;
    private Double cartItemPrice;
    private Long cartItemQuantity;
}

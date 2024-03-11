package com.microservices.cart.dtos;

import com.microservices.cart.entities.Cart;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class AddCartItemResponse {
    private Long cartItemId;
    private String cartItemName;
    private Double cartItemPrice;
    private Long cartItemQuantity;
    private Cart cart;
}

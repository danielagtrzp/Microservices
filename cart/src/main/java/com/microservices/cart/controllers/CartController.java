package com.microservices.cart.controllers;

import com.microservices.cart.dtos.AddCartItemResponse;
import com.microservices.cart.dtos.GetCartItemsByUserResponse;
import com.microservices.cart.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @PutMapping("/courses/{courseId}/users/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public AddCartItemResponse addCartItemToCart(@PathVariable Long courseId, @PathVariable Long userId, @RequestParam(required = false) Long quantity) {
        return cartService.addCartItemToCart(courseId,userId,quantity);
    }

    @DeleteMapping("/{carItemId}/users/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCartItem(@PathVariable Long carItemId, @PathVariable Long userId) {
        cartService.deleteCartItem(carItemId,userId);
    }

    @GetMapping("/users/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<GetCartItemsByUserResponse> getCartItemsByUser(@PathVariable Long userId){
        return cartService.getCartItemsByUser(userId);
    }

}

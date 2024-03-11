package com.microservices.cart.services;

import com.microservices.cart.dtos.*;
import com.microservices.cart.entities.Cart;
import com.microservices.cart.entities.CartItem;
import com.microservices.cart.externals.CartToCourseService;
import com.microservices.cart.mappers.CartItemMapper;
import com.microservices.cart.repositories.CartItemRepository;
import com.microservices.cart.repositories.CartRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private  CartRepository cartRepository;
    @Autowired
    private  CartItemRepository cartItemRepository;
    @Autowired
    private CartToCourseService cartToCourseService;


    public AddCartItemResponse addCartItemToCart(Long courseId, Long userId, Long quantity) {
        //seek course
        CartCourseFeignResponse cartCourseFeignResponse = cartToCourseService.getCourseById(courseId);
        //seek cart by user
        Cart cart = cartRepository.findByUserId(userId);
        CartItem cartItem = CartItemMapper.INSTANCE.toCartItem(cartCourseFeignResponse);
        cartItem.setCart(cart);
        cartItem.setCartItemQuantity(quantity);
        cartItemRepository.save(cartItem);
        //add cart Item to cart
        cart.getCartItems().add(cartItem);

        Cart savedCart = cartRepository.save(cart);

        return CartItemMapper.INSTANCE.toAddCartResponse(cartItem);
    }

    public void deleteCartItem(Long cartItemId, Long userId) {
        Cart cart = cartRepository.findByUserId(userId);
        Optional<CartItem> cartItem = cartItemRepository.findById(cartItemId);
        if (cartItem.isEmpty()){
            throw  new EntityNotFoundException();
        }
        cart.getCartItems().remove(cartItem.get());
        cartItemRepository.delete(cartItem.get());
    }

    public List<GetCartItemsByUserResponse> getCartItemsByUser(Long userId) {
        Cart cart = cartRepository.findByUserId(userId);
        return CartItemMapper.INSTANCE.toGetCartItemsByUserResponse(cart.getCartItems());
    }
}

package com.microservices.cart.services;

import com.microservices.cart.dtos.AddCartItemResponse;
import com.microservices.cart.dtos.CartCourseFeignResponse;
import com.microservices.cart.entities.Cart;
import com.microservices.cart.entities.CartItem;
import com.microservices.cart.externals.CartToCourseService;
import com.microservices.cart.mappers.CartItemMapper;
import com.microservices.cart.repositories.CartItemRepository;
import com.microservices.cart.repositories.CartRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {

    @Mock
    CartRepository cartRepository;
    @Mock
    CartItemRepository cartItemRepository;
    @Mock
    CartToCourseService cartToCourseService;

    @InjectMocks
    CartService cartService;


    @Test
    void addCartItemToCart() {
        Long courseId = 1L;
        Long userId = 1L;
        Long quantity = 1L;

        CartCourseFeignResponse cartCourseFeignResponse = new CartCourseFeignResponse(10L,"COURSENAME",100.0);
        Cart cart = new Cart();
        CartItem cartItem = new CartItem();

        given(cartToCourseService.getCourseById(anyLong())).willReturn(cartCourseFeignResponse);
        given(cartRepository.findByUserId(anyLong())).willReturn(cart);
        given(cartItemRepository.save(any())).willReturn(cartItem);
        given(cartRepository.save(any())).willReturn(cart);

        AddCartItemResponse result = cartService.addCartItemToCart(courseId, userId, quantity);

        verify(cartToCourseService).getCourseById(any());
        verify(cartRepository).findByUserId(any());
        verify(cartItemRepository).save(any(CartItem.class));
        verify(cartRepository).save(any(Cart.class));

        assertNotNull(result);

    }

    @Test
    void deleteCartItem() {

        given(cartRepository.findByUserId(anyLong())).willReturn(new Cart());
        given(cartItemRepository.findById(anyLong())).willReturn(Optional.of(new CartItem()));

        cartService.deleteCartItem(1L,1L);

        verify(cartItemRepository).findById(anyLong());
        verify(cartRepository).findByUserId(anyLong());
        verify(cartItemRepository).delete(any());
    }
}
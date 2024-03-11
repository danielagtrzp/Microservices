package com.microservices.cart.controllers;

import com.microservices.cart.dtos.AddCartItemResponse;
import com.microservices.cart.exceptions.GlobalExceptionController;
import com.microservices.cart.services.CartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class CartControllerTest {

    @Mock
    CartService cartService;

    @InjectMocks
    CartController cartController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(cartController)
                .setControllerAdvice(GlobalExceptionController.class)
                .build();
    }

    @Test
    void addCartItemToCart() throws Exception {
        AddCartItemResponse addCartItemToCartResponse = new AddCartItemResponse();
        given(cartService.addCartItemToCart(anyLong(),anyLong(),anyLong())).willReturn(addCartItemToCartResponse);

        mockMvc.perform(put("/api/carts/courses/1/users/1")
                .contentType(APPLICATION_JSON)
                .param("quantity","1"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteCartItem() throws Exception {
        mockMvc.perform(delete("/api/carts/1/users/1"))
                .andExpect(status().isOk());
    }
}
package com.microservices.order.controllers;

import com.microservices.order.dtos.CreateOrderResponse;
import com.microservices.order.exceptions.GlobalExceptionController;
import com.microservices.order.externals.OrderCartService;
import com.microservices.order.services.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

    @Mock
    OrderService orderService;

    @Mock
    OrderCartService orderCartService;

    @InjectMocks
    OrderController orderController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(orderController)
                .setControllerAdvice(GlobalExceptionController.class)
                .build();
    }

    @Test
    void createOrder() throws Exception {

        given(orderService.createOrder(anyLong())).willReturn(new CreateOrderResponse());

        mockMvc.perform(post("/api/orders/users/1"))
                .andExpect(status().isCreated());

        verify(orderService).createOrder(anyLong());
    }
}
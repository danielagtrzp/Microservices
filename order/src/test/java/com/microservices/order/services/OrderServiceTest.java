package com.microservices.order.services;

import com.microservices.order.dtos.CreateOrderResponse;
import com.microservices.order.dtos.OrderCartFeignResponse;
import com.microservices.order.entities.Order;
import com.microservices.order.entities.OrderItem;
import com.microservices.order.externals.OrderCartService;
import com.microservices.order.mappers.OrderMapper;
import com.microservices.order.repositories.OrderItemRepository;
import com.microservices.order.repositories.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    OrderRepository orderRepository;
    @Mock
    OrderCartService orderCartService;

    @Mock
    OrderItemRepository orderItemRepository;

    //@Spy
    //OrderMapper orderMapper = Mappers.getMapper(OrderMapper.class);


    @InjectMocks
    OrderService orderService;

    @Test
    void createOrder() {
        List<OrderCartFeignResponse> feignResponses = List.of();
        List<OrderItem> orderItems = List.of();
        Order savedOrder =new Order();

        given(orderCartService.getCartItemsByUser(anyLong())).willReturn(feignResponses);
        given(orderRepository.save(any())).willReturn(savedOrder);


        CreateOrderResponse result = orderService.createOrder(1L);

        verify(orderCartService).getCartItemsByUser(anyLong());
        verify(orderRepository).save(any());
        verify(orderItemRepository).saveAll(any());
    }
}
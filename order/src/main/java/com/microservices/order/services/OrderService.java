package com.microservices.order.services;

import com.microservices.order.dtos.CreateOrderResponse;
import com.microservices.order.dtos.OrderCartFeignResponse;
import com.microservices.order.entities.Order;
import com.microservices.order.entities.OrderItem;
import com.microservices.order.externals.OrderCartService;
import com.microservices.order.mappers.OrderMapper;
import com.microservices.order.repositories.OrderItemRepository;
import com.microservices.order.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderCartService orderCartService;
    @Autowired
    private OrderItemRepository orderItemRepository;

    public CreateOrderResponse createOrder(Long userId) {

        List<OrderCartFeignResponse> orderCartItemsFeignResponse = orderCartService.getCartItemsByUser(userId);
        List<OrderItem> orderItems = OrderMapper.INSTANCE.toOrderItems(orderCartItemsFeignResponse);
        Double total = orderItems.stream()
                .map(OrderItem::getOrderItemPrice)
                .reduce(0.0,Double::sum);
        Order order = new Order();
        order.getOrderItems().addAll(orderItems);
        order.setTotalPrice(total);
        order.setUser(userId);
        Order orderSaved = orderRepository.save(order);
        orderItems.stream()
                .forEach(orderItem -> orderItem.setOrder(orderSaved));
        orderItemRepository.saveAll(orderItems);
        return OrderMapper.INSTANCE.toCreateOrderResponse(orderSaved);
    }
}

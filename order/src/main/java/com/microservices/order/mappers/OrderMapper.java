package com.microservices.order.mappers;

import com.microservices.order.dtos.CreateOrderResponse;
import com.microservices.order.dtos.OrderCartFeignResponse;
import com.microservices.order.dtos.OrderItemKafka;
import com.microservices.order.entities.Order;
import com.microservices.order.entities.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(source = "cartItemName",target = "orderItemName")
    @Mapping(source = "cartItemPrice",target = "orderItemPrice")
    @Mapping(source = "cartItemQuantity",target = "orderItemQuantity")
    OrderItem toOrderItem(OrderCartFeignResponse orderCartFeignResponse);


    List<OrderItem> toOrderItems(List<OrderCartFeignResponse> orderCartItemsFeignResponse);

    OrderItemKafka toOrderItemsKafka(OrderItem orderItem);

    List<OrderItemKafka> toCreateOrderItemKafka(List<OrderItem> orderItems);

    CreateOrderResponse toCreateOrderResponse(Order orderSaved);

}

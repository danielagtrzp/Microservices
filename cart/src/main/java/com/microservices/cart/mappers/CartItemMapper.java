package com.microservices.cart.mappers;

import com.microservices.cart.dtos.AddCartItemRequest;
import com.microservices.cart.dtos.AddCartItemResponse;
import com.microservices.cart.dtos.CartCourseFeignResponse;
import com.microservices.cart.entities.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CartItemMapper {
    CartItemMapper INSTANCE = Mappers.getMapper(CartItemMapper.class);


    @Mapping(source = "courseName",target = "cartItemName")
    @Mapping(source = "coursePrice",target = "cartItemPrice")
    CartItem toCartItem(CartCourseFeignResponse cartCourseFeignResponse);

    AddCartItemResponse toAddCartResponse(CartItem cartItem);
}

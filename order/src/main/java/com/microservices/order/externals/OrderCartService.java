package com.microservices.order.externals;

import com.microservices.order.dtos.OrderCartFeignResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "CART-SERVICE")
public interface OrderCartService {

    @GetMapping("/api/carts/users/{userId}")
    public List<OrderCartFeignResponse> getCartItemsByUser(@PathVariable Long userId);
}

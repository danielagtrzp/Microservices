package com.microservices.cart.externals;

import com.microservices.cart.dtos.AddCartItemResponse;
import com.microservices.cart.dtos.CartCourseFeignResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@FeignClient(name = "COURSE-SERVICE")
public interface CartToCourseService {

    @GetMapping("/api/courses/{id}")
    CartCourseFeignResponse getCourseById(@PathVariable Long id);

}

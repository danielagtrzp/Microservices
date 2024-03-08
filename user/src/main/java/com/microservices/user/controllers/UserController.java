package com.microservices.user.controllers;

import com.microservices.user.dtos.CreateUserRequest;
import com.microservices.user.dtos.CreateUserResponse;
import com.microservices.user.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateUserResponse createUser(@Valid @RequestBody CreateUserRequest createUserRequest) throws Exception {
        return userService.createUser(createUserRequest);
    }

}

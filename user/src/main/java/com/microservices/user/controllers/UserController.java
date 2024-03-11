package com.microservices.user.controllers;

import com.microservices.user.dtos.*;
import com.microservices.user.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UpdateUserResponse updateUser(@PathVariable Long id, @RequestBody UpdateUserRequest updateUserRequest) throws Exception {

        return userService.updateUser(id, updateUserRequest);
    }

    @GetMapping("/{id}/courses")
    @ResponseStatus(HttpStatus.OK)
    public UserDetailResponse getUserDetailsAndCourses(@PathVariable Long id){
        return userService.getUserDetailsAndCourses(id);
    }

    @GetMapping("/{id}/courses/domain")
    @ResponseStatus(HttpStatus.OK)
    public List<GetUserCoursesRecommendedByDomainResponse> getUserCoursesRecommendedByDomain(@PathVariable Long id){
        return userService.getUserCoursesRecommendedByDomain(id);
    }
}

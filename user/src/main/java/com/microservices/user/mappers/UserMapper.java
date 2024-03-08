package com.microservices.user.mappers;

import com.microservices.user.dtos.CreateUserRequest;
import com.microservices.user.dtos.CreateUserResponse;
import com.microservices.user.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    //createUser
    CreateUserRequest toCreateUserRequest(User user);
    CreateUserResponse toCreateUserResponse(User user);
    User toUser(CreateUserRequest createUserRequest);
    User toUser(CreateUserResponse createUserResponse);
}

package com.microservices.user.mappers;

import com.microservices.user.dtos.*;
import com.microservices.user.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    //createUser
    CreateUserRequest toCreateUserRequest(User user);
    CreateUserResponse toCreateUserResponse(User user);
    User toUser(CreateUserRequest createUserRequest);
    User toUser(CreateUserResponse createUserResponse);

    //updateUser
    User toUser(UpdateUserRequest updateUserRequest);
    UpdateUserResponse toUpdateUserResponse(User user);
    void updateUserFromRequest(UpdateUserRequest updateUserRequest,@MappingTarget User user);

    //getUserDetailsAndCourses
    UserDetailResponse toUserDetailResponse(User user);
}

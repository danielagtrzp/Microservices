package com.microservices.user.services;

import com.microservices.user.dtos.CreateUserRequest;
import com.microservices.user.dtos.CreateUserResponse;
import com.microservices.user.entities.User;
import com.microservices.user.mappers.UserMapper;
import com.microservices.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    public CreateUserResponse createUser(CreateUserRequest createUserRequest) throws Exception {
        User user = UserMapper.INSTANCE.toUser(createUserRequest);
        User userCreated = userRepository.save(user);
        CreateUserResponse createUserResponse = UserMapper.INSTANCE.toCreateUserResponse(userCreated);
        return createUserResponse;
    }
}

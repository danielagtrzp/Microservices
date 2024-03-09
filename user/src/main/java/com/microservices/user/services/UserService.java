package com.microservices.user.services;

import com.microservices.user.dtos.*;
import com.microservices.user.entities.User;
import com.microservices.user.mappers.UserMapper;
import com.microservices.user.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public CreateUserResponse createUser(CreateUserRequest createUserRequest){
        User user = UserMapper.INSTANCE.toUser(createUserRequest);
        User userCreated = userRepository.save(user);
        return UserMapper.INSTANCE.toCreateUserResponse(userCreated);
    }

    public UpdateUserResponse updateUser(Long id, UpdateUserRequest updateUserRequest) throws Exception {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found: " + id));
        UserMapper.INSTANCE.updateUserFromRequest(updateUserRequest, user);
        User finalUser = userRepository.save(user);
        UpdateUserResponse updateUserResponse = UserMapper.INSTANCE.toUpdateUserResponse(finalUser);
        return updateUserResponse;
    }

}

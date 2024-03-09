package com.microservices.user.services;

import com.microservices.user.dtos.CreateUserRequest;
import com.microservices.user.dtos.UpdateUserRequest;
import com.microservices.user.entities.User;
import com.microservices.user.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Test
    void createUser() throws Exception {

        User userCreated =new User();
        CreateUserRequest createUserRequest =new CreateUserRequest();

        given(userRepository.save(any())).willReturn(userCreated);

        userService.createUser(createUserRequest);

        verify(userRepository).save(any());
    }

    @Test
    void updateUser() throws Exception {

        User user = new User();
        UpdateUserRequest updateUserRequest = new UpdateUserRequest();

        given(userRepository.findById(anyLong())).willReturn(Optional.of(user));
        given(userRepository.save(any())).willReturn(user);

        userService.updateUser(anyLong(), updateUserRequest);

        verify(userRepository).findById(anyLong());
        verify(userRepository).save(any());
    }
}
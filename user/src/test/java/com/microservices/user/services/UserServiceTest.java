package com.microservices.user.services;

import com.microservices.user.dtos.CreateUserRequest;
import com.microservices.user.entities.User;
import com.microservices.user.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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

}
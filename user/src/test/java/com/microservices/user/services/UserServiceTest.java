package com.microservices.user.services;

import com.microservices.user.dtos.CourseResponse;
import com.microservices.user.dtos.CreateUserRequest;
import com.microservices.user.dtos.UpdateUserRequest;
import com.microservices.user.dtos.UserDetailResponse;
import com.microservices.user.entities.User;
import com.microservices.user.externals.UserCoursesService;

import com.microservices.user.repositories.UserRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    UserCoursesService userCoursesService;

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
    void createUser_UserAlreadyExist() {

        given(userRepository.save(any())).willThrow(EntityExistsException.class);

        assertThrows(EntityExistsException.class, () -> userService.createUser(new CreateUserRequest()));
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


    @Test
    void getUserDetailsAndCourses() {
        User user = new User();
        List<CourseResponse> expectedCourses = List.of(new CourseResponse(), new CourseResponse());
        given(userRepository.findById(anyLong())).willReturn(Optional.of(user));
        given(userCoursesService.getUserCourses(anyLong())).willReturn(expectedCourses);

        UserDetailResponse actualResponse = userService.getUserDetailsAndCourses(1L);

        assertEquals(expectedCourses, actualResponse.getCourses());
    }

    @Test
    void getUserDetailsAndCourses_UserDoesNotExist() {

        given(userRepository.findById(anyLong())).willReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> userService.getUserDetailsAndCourses(1L));
    }
}
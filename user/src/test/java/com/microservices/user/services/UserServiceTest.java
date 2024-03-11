package com.microservices.user.services;

import com.microservices.user.dtos.*;
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
import org.springframework.data.domain.Sort;

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
        List<UserCourseFeignResponse> expectedCourses = List.of(new UserCourseFeignResponse(), new UserCourseFeignResponse());
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

    @Test
    void getUserCoursesRecommendedByDomain() {
        given(userRepository.findById(anyLong())).willReturn(Optional.of(new User()));
        List<GetUserCoursesRecommendedByDomainResponse> courses= List.of(new GetUserCoursesRecommendedByDomainResponse());
        Sort sort = Sort.by(Sort.Direction.DESC, "coursePrice");
        given(userCoursesService.getCoursesFilteredAndSorted(any(),any(),eq(sort))).willReturn(courses);

        userService.getUserCoursesRecommendedByDomain(1L);

        verify(userRepository).findById(anyLong());
        verify(userCoursesService).getCoursesFilteredAndSorted(anyString(),anyString(),eq(sort));
    }
}
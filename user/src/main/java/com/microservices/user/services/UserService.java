package com.microservices.user.services;

import com.microservices.user.dtos.*;
import com.microservices.user.entities.User;
import com.microservices.user.externals.UserCoursesService;
import com.microservices.user.mappers.UserMapper;
import com.microservices.user.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserCoursesService userCoursesService;


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

    public UserDetailResponse getUserDetailsAndCourses(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isEmpty()){
            throw new EntityNotFoundException();
        }
        User user = optionalUser.get();
        UserDetailResponse userDetailResponse = UserMapper.INSTANCE.toUserDetailResponse(user);
        List<CourseResponse> userCourses = userCoursesService.getUserCourses(id);
        userDetailResponse.setCourses(userCourses);
        return  userDetailResponse;
    }
}

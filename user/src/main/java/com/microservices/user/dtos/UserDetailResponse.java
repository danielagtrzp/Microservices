package com.microservices.user.dtos;

import com.microservices.user.entities.AreasOfInterest;
import com.microservices.user.entities.Role;
import com.microservices.user.entities.UserType;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class UserDetailResponse {
    private Long userId;
    private String userName;
    private String firstName;
    private String lastName;
    private String bio;
    private List<AreasOfInterest> areasOfInterest = new ArrayList<>();
    private UserType userType;
    private Role role;
    private String domainExpertise;
    private String profileImage;
    private List<CourseResponse> courses;
}

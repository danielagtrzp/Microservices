package com.microservices.user.dtos;

import com.microservices.user.entities.AreasOfInterest;
import com.microservices.user.entities.Role;
import com.microservices.user.entities.UserType;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CreateUserResponse {
    private Long userId;
    private String userName;
    private String firstName;
    private String lastName;
    private String bio;
    private List<AreasOfInterest> AreasOfInterest;
    private UserType userType;
    private Role role;
    private String domainExpertise;
    private String profileImage;
}

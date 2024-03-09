package com.microservices.user.dtos;

import com.microservices.user.entities.AreasOfInterest;
import com.microservices.user.entities.Role;
import com.microservices.user.entities.UserType;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class UpdateUserRequest {
    private String bio;
    private List<AreasOfInterest> areasOfInterest;
    private UserType userType;
    private Role role;
    private String domainExpertise;
    private String profileImage;
}

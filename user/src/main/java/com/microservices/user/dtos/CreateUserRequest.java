package com.microservices.user.dtos;

import com.microservices.user.entities.AreasOfInterest;
import com.microservices.user.entities.Role;
import com.microservices.user.entities.UserType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CreateUserRequest {
    private Long userId;
    @NotNull
    @NotBlank
    @Size(min = 3)
    private String userName;
    @NotNull
    @NotBlank
    @Size(min = 3)
    private String firstName;
    @NotNull
    @NotBlank
    @Size(min = 3)
    private String lastName;
    private String bio;
    private List<AreasOfInterest> AreasOfInterest;
    private UserType userType;
    private Role role;
    private String domainExpertise;
    private String profileImage;
}

package com.microservices.user.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CreateUserRequest {

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
}

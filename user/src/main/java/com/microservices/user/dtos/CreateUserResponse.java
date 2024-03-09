package com.microservices.user.dtos;

import lombok.*;



@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CreateUserResponse {
    private String userName;
    private String firstName;
    private String lastName;
}

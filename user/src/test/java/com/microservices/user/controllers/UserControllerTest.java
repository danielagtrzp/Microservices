package com.microservices.user.controllers;

import com.microservices.user.dtos.CreateUserResponse;
import com.microservices.user.entities.AreasOfInterest;
import com.microservices.user.entities.Role;
import com.microservices.user.entities.UserType;
import com.microservices.user.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;


import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    UserService userService;

    @InjectMocks
    UserController userController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }



    @Test
    void createUser() throws Exception {
        List<AreasOfInterest> areasOfInterest = List.of();
        UserType userType = new UserType();
        Role role = new Role();

        CreateUserResponse createUserResponse = new CreateUserResponse(1L, "UserName", "FirstName", "LastName", "Bio", areasOfInterest, userType, role, "DomainExpertise", "ProfileImage");
        given(userService.createUser(any())).willReturn(createUserResponse);

        mockMvc.perform(post("/users")
                        .contentType(APPLICATION_JSON)
                        .content("{\n" +
                                "  \"userName\": \"Danielagtrzp\",\n" +
                                "  \"firstName\": \"Daniela\",\n" +
                                "  \"lastName\": \"Gutierrez\"\n" +
                                "}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").isMap());

        verify(userService).createUser(any());

    }
}
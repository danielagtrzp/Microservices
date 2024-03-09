package com.microservices.user.controllers;

import com.microservices.user.dtos.CreateUserResponse;
import com.microservices.user.dtos.UpdateUserResponse;
import com.microservices.user.dtos.UserDetailResponse;
import com.microservices.user.entities.AreasOfInterest;
import com.microservices.user.entities.Role;
import com.microservices.user.entities.UserType;
import com.microservices.user.exception.GlobalExceptionController;
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
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                                .setControllerAdvice(GlobalExceptionController.class)
                                .build();
    }



    @Test
    void createUser() throws Exception {

        CreateUserResponse createUserResponse = new CreateUserResponse( "UserName", "FirstName", "LastName");
        given(userService.createUser(any())).willReturn(createUserResponse);

        mockMvc.perform(post("/api/users")
                        .contentType(APPLICATION_JSON)
                        .content("""
                                {
                                  "userName": "Danielagtrzp",
                                  "firstName": "Daniela",
                                  "lastName": "Gutierrez"
                                }"""))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").isMap());

        verify(userService).createUser(any());

    }

    @Test
    void updateUser() throws Exception {
        List<AreasOfInterest> areasOfInterest = List.of();
        UserType userType = new UserType();
        Role role = new Role();

        UpdateUserResponse updateUserResponse = new UpdateUserResponse(1L,"dani","dani","gut","",areasOfInterest,userType,role,"","");
        given(userService.updateUser(anyLong(),any())).willReturn(updateUserResponse);

        mockMvc.perform(put("/api/users/1")
                        .contentType(APPLICATION_JSON)
                        .content("""
                                {
                                 "areasOfInterest": [
                                     {
                                         "name": "NombreDelAreaDeInteres",
                                         "description": "DescripciónDelAreaDeInteres"
                                     }
                                 ],
                                 "userType": {
                                     "name": "NombreDelTipoDeUsuario",
                                     "description": "DescripciónDelTipoDeUsuario"
                                 },
                                 "role": {
                                     "name": "NombreDelRol",
                                     "description": "DescripciónDelRol"
                                 },
                                 "domainExpertise": "ExpertoEnDominio",
                                 "profileImage": "URLDeImagenDePerfil"
                                }"""))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isMap());

        verify(userService).updateUser(anyLong(),any());
    }


    @Test
    void getUserDetailsAndCourses() throws Exception {
        UserDetailResponse userDetailResponse = new UserDetailResponse();
        given(userService.getUserDetailsAndCourses(anyLong())).willReturn(userDetailResponse);

        mockMvc.perform(get("/api/users/1/courses"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isMap());

        verify(userService).getUserDetailsAndCourses(anyLong());
    }
}
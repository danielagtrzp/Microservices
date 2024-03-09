package com.microservices.course.controllers;

import com.microservices.course.entities.Course;
import com.microservices.course.exceptions.GlobalExceptionController;
import com.microservices.course.services.CourseService;
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
class CourseControllerTest {

    @Mock
    CourseService courseService;

    @InjectMocks
    CourseController courseController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(courseController)
                .setControllerAdvice(GlobalExceptionController.class)
                .build();
    }

    @Test
    void getUserCourses() throws Exception {
        List<Course> courses= List.of(new Course(),new Course());
        given(courseService.getUserCourses(anyLong())).willReturn(courses);

        mockMvc.perform(get("/api/courses/users/1")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isNotEmpty());

        verify(courseService).getUserCourses(anyLong());
    }
}
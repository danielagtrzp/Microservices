package com.microservices.course.controllers;

import com.microservices.course.dtos.AddCourseResponse;
import com.microservices.course.dtos.GetUserCoursesResponse;
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
        List<GetUserCoursesResponse> courses= List.of(new GetUserCoursesResponse(),new GetUserCoursesResponse());
        given(courseService.getUserCourses(anyLong())).willReturn(courses);

        mockMvc.perform(get("/api/courses/users/1")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isNotEmpty());

        verify(courseService).getUserCourses(anyLong());
    }

    @Test
    void addCourse() throws Exception {
        AddCourseResponse addCourseResponse = new AddCourseResponse();
        given(courseService.addCourse(any())).willReturn(addCourseResponse);

        mockMvc.perform(post("/api/courses")
                .contentType(APPLICATION_JSON)
                .content("""
                        {
                          "courseName": "Introduction to Programming",
                          "courseDescription": "This course provides an introduction to programming and software development.",
                          "courseAuthor": "John Doe",
                          "coursePrice": 99.99,
                          "courseDuration": 30.0
                        }"""
                )).andExpect(status().isCreated())
                .andExpect(jsonPath("$").isMap());
    }

    @Test
    void deleteCourse() throws Exception {

        mockMvc.perform(delete("/api/courses/1"))
                .andExpect(status().isOk());
    }
}
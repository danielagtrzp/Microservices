package com.microservices.course.controllers;

import com.microservices.course.dtos.*;
import com.microservices.course.exceptions.GlobalExceptionController;
import com.microservices.course.services.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @Test
    void getCoursesFilteredAndSorted() throws Exception {
        List<GetCoursesFilteredAndSortedResponse> courses = List.of(
                new GetCoursesFilteredAndSortedResponse(10L,"name1","desc1","aut1","dom1",1.0,1.0),
                new GetCoursesFilteredAndSortedResponse(10L,"name1","desc2","aut2","dom2",2.0,2.0)
                );
        Sort sort = Sort.by(Sort.Direction.DESC, "coursePrice");
        given(courseService.getCoursesFilteredAndSorted(any(),any(), eq(sort))).willReturn(courses);

        List<GetCoursesFilteredAndSortedResponse> actualCourses = courseController.getCoursesFilteredAndSorted(null, null, sort);

        assertEquals(courses, actualCourses);
        verify(courseService).getCoursesFilteredAndSorted(any(), any(), eq(sort));

    }

    @Test
    void addReview() throws Exception {
        given(courseService.addReview(anyLong(),anyLong(),any())).willReturn(new AddReviewResponse());

        mockMvc.perform(put("/api/courses/1/reviews/users/1")
                .contentType(APPLICATION_JSON)
                .content("""
                        {
                            "review":"is aweasome"
                        }"""))
                .andExpect(status().isOk());

        verify(courseService).addReview(anyLong(),anyLong(),any());
    }

    @Test
    void addRating() throws Exception {
        given(courseService.addRating(anyLong(),anyLong(),any())).willReturn(new AddRatingResponse());

        mockMvc.perform(put("/api/courses/1/ratings/users/1")
                        .contentType(APPLICATION_JSON)
                        .content("""
                        {
                            "rating":4.0
                        }"""))
                .andExpect(status().isOk());

        verify(courseService).addRating(anyLong(),anyLong(),any());
    }

    @Test
    void getCourseById() throws Exception {
        GetCourseByIdResponse courses = new GetCourseByIdResponse();
        given(courseService.getCourseById(anyLong())).willReturn(courses);

        mockMvc.perform(get("/api/courses/1"))
                .andExpect(status().isOk());

        verify(courseService).getCourseById(anyLong());
    }

    @Test
    void getCourseDetailsById() throws Exception {

        GetCourseDetailsByIdResponse courses = new GetCourseDetailsByIdResponse();
        given(courseService.getCourseDetailsById(anyLong())).willReturn(courses);

        mockMvc.perform(get("/api/courses/1/details"))
                .andExpect(status().isOk());

        verify(courseService).getCourseDetailsById(anyLong());
    }

    @Test
    void getCoursesDetailsByUserId() throws Exception {
        List<GetCourseByUserIdResponse> courses= List.of();
        given(courseService.getCoursesDetailsByUserId(anyLong())).willReturn(courses);

        mockMvc.perform(get("/api/courses/users/1/details")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(courseService).getCoursesDetailsByUserId(anyLong());
    }

    @Test
    void getCoursesSortedByPerformance() throws Exception {
        List<GetCoursesSortedByPerformanceResponse> courses= List.of();
        Sort sort = Sort.by(Sort.Direction.DESC, "coursePrice");
        given(courseService.getCoursesSortedByPerformance(eq(sort))).willReturn(courses);

       courseService.getCoursesSortedByPerformance(sort);

        verify(courseService).getCoursesSortedByPerformance(any(Sort.class));
    }

    @Test
    void updatePerformance() throws Exception {
        given(courseService.updatePerformance(anyLong())).willReturn(new UpdatePerformanceResponse());

        mockMvc.perform(put("/api/courses/1"))
                .andExpect(status().isOk());

        verify(courseService).updatePerformance(anyLong());
    }

}
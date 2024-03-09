package com.microservices.course.services;

import com.microservices.course.dtos.AddCourseRequest;
import com.microservices.course.dtos.GetUserCoursesResponse;
import com.microservices.course.entities.Course;
import com.microservices.course.entities.Student;
import com.microservices.course.repositories.CourseRepository;
import com.microservices.course.repositories.StudentRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CourseServiceTest {

    @Mock
    CourseRepository courseRepository;

    @Mock
    StudentRepository studentRepository;

    @InjectMocks
    CourseService courseService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getUserCourses() {
        List<Course> courses = List.of(new Course(),new Course());
        List<GetUserCoursesResponse> getUserCoursesResponses = List.of(new GetUserCoursesResponse(),new GetUserCoursesResponse());
        Student student = new Student();
        student.setCourses(courses);
        given(studentRepository.findById(anyLong())).willReturn(Optional.of(student));

        List<GetUserCoursesResponse> finalCourses = courseService.getUserCourses(anyLong());


        verify(studentRepository).findById(anyLong());
    }

    @Test
    void getUserCourses_StudentDoesNotExist() {

        given(studentRepository.findById(anyLong())).willReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> courseService.getUserCourses(1L));
    }

    @Test
    void addCourse() {
        AddCourseRequest addCourseRequest = new AddCourseRequest();
        Course course = new Course();
        given(courseRepository.save(any())).willReturn(course);

        courseService.addCourse(addCourseRequest);

        verify(courseRepository).save(any());
    }

    @Test
    void addCourse_CourseAlreadyExist() {
        AddCourseRequest addCourseRequest = new AddCourseRequest();
        Course course = new Course();
        given(courseRepository.save(any())).willThrow(EntityExistsException.class);

        assertThrows(EntityExistsException.class, () -> courseService.addCourse(addCourseRequest));
    }

    @Test
    void deleteCourse() {

        when(courseRepository.findById(anyLong())).thenReturn(Optional.of(new Course()));
        doNothing().when(courseRepository).deleteById(anyLong());

        courseService.deleteCourse(anyLong());

        verify(courseRepository).findById(anyLong());
        verify(courseRepository).deleteById(anyLong());

    }

    @Test
    void deleteCourse_CourseNotFound() {
        given(courseRepository.findById(anyLong())).willReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,()-> courseService.deleteCourse(anyLong()));

    }
}
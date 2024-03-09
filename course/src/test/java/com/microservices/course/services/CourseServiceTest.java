package com.microservices.course.services;

import com.microservices.course.entities.Course;
import com.microservices.course.entities.Student;
import com.microservices.course.repositories.CourseRepository;
import com.microservices.course.repositories.StudentRepository;
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
        Student student = new Student();
        student.setCourses(courses);
        given(studentRepository.findById(anyLong())).willReturn(Optional.of(student));

        List<Course> finalCourses = courseService.getUserCourses(anyLong());

        assertEquals(courses,finalCourses);

        verify(studentRepository).findById(anyLong());
    }

    @Test
    void getUserCourses_StudentDoesNotExist() {

        given(studentRepository.findById(anyLong())).willReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> courseService.getUserCourses(1L));
    }
}
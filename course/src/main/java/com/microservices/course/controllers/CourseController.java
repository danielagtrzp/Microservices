package com.microservices.course.controllers;

import com.microservices.course.entities.Course;
import com.microservices.course.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/users/{id}")
    public List<Course> getUserCourses(@PathVariable Long id) {
        List<Course> courses = courseService.getUserCourses(id);
        return courses;
    }
}

package com.microservices.course.controllers;

import com.microservices.course.dtos.AddCourseRequest;
import com.microservices.course.dtos.AddCourseResponse;
import com.microservices.course.dtos.GetUserCoursesResponse;
import com.microservices.course.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/users/{id}")
    public List<GetUserCoursesResponse> getUserCourses(@PathVariable Long id) {
        List<GetUserCoursesResponse> courses = courseService.getUserCourses(id);
        return courses;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AddCourseResponse addCourse(@RequestBody AddCourseRequest course) {
        return courseService.addCourse(course);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCourse(@PathVariable Long id) {
       courseService.deleteCourse(id);
    }
}

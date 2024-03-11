package com.microservices.course.controllers;

import com.microservices.course.dtos.*;
import com.microservices.course.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetCourseByIdResponse getCourseById(@PathVariable Long id){
        return courseService.getCourseById(id);
    }

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

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetCoursesFilteredAndSortedResponse> getCoursesFilteredAndSorted(
            @RequestParam(required = false) String courseName,
            @RequestParam(required = false) String domain,
            @SortDefault.SortDefaults({
                        @SortDefault(sort = "coursePrice", direction = Sort.Direction.ASC),
                        @SortDefault(sort = "courseName", direction = Sort.Direction.ASC)
            }) Sort sort)  {
        return courseService.getCoursesFilteredAndSorted(courseName,domain,sort);
    }

    @PutMapping("/{courseId}/reviews/users/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public AddReviewResponse addReview(@PathVariable Long courseId ,@PathVariable Long userId, @RequestBody AddReviewRequest addReviewRequest) {
        return courseService.addReview(courseId, userId, addReviewRequest);
    }


    @PutMapping("/{courseId}/ratings/users/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public AddRatingResponse addRating(@PathVariable Long courseId, @PathVariable Long userId, @RequestBody AddRatingRequest addRatingRequest) {
        return courseService.addRating(courseId, userId,addRatingRequest);
    }
}

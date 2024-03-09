package com.microservices.user.externals;

import com.microservices.user.dtos.CourseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "COURSE-SERVICE")
public interface UserCoursesService {

    @GetMapping("/api/courses/users/{id}")
    List<CourseResponse> getUserCourses(@PathVariable Long id);
}

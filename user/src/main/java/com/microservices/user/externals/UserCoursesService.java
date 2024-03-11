package com.microservices.user.externals;

import com.microservices.user.dtos.GetUserCoursesRecommendedByDomainResponse;
import com.microservices.user.dtos.UserCourseFeignResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "COURSE-SERVICE")
public interface UserCoursesService {

    @GetMapping("/api/courses/users/{id}")
    List<UserCourseFeignResponse> getUserCourses(@PathVariable Long id);

    @GetMapping("/api/courses")
    List<GetUserCoursesRecommendedByDomainResponse> getCoursesFilteredAndSorted(
            @RequestParam(required = false) String courseName,
            @RequestParam(required = false) String domain,
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "coursePrice", direction = Sort.Direction.ASC),
                    @SortDefault(sort = "courseName", direction = Sort.Direction.ASC)
            }) Sort sort);
}

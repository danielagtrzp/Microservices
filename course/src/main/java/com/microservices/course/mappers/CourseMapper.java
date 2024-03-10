package com.microservices.course.mappers;

import com.microservices.course.dtos.AddCourseRequest;
import com.microservices.course.dtos.AddCourseResponse;
import com.microservices.course.dtos.GetCoursesFilteredAndSortedResponse;
import com.microservices.course.dtos.GetUserCoursesResponse;
import com.microservices.course.entities.Course;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CourseMapper {

    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    //addCourse
    Course toCourse(AddCourseRequest addCourseRequest);

    AddCourseResponse toCourseResponse(Course courseSaved);

    List<GetUserCoursesResponse> toGetUserCoursesResponse(List<Course> courses);

    List<GetCoursesFilteredAndSortedResponse> toGetCoursesFilteredAndSortedResponse(List<Course> courses);
}

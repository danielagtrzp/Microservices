package com.microservices.course.mappers;

import com.microservices.course.dtos.*;
import com.microservices.course.entities.Course;
import com.microservices.course.entities.Rating;
import com.microservices.course.entities.Review;
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

    GetCourseByIdResponse toGetCourseByIdResponse(Course course);

    Review toReview(AddReviewRequest addReviewRequest);

    AddReviewResponse toAddReviewResponse(Review reviewSaved);

    Rating toRating(AddRatingRequest addRatingRequest);

    AddRatingResponse toAddRatingResponse(Rating ratingSaved);

    GetCourseDetailsByIdResponse toGetCourseDetailsByIdResponse(Course course);
}

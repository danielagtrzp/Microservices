package com.microservices.course.dtos;

import com.microservices.course.entities.Rating;
import com.microservices.course.entities.Review;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class GetCourseByUserIdResponse {

    private Long courseId;
    private String courseName;
    private String courseDescription;
    private String courseAuthor;
    private Double coursePrice;
    private Double courseDuration;
    private List<ReviewDto> courseReviews = new ArrayList<>();
    private List<RatingDto> courseRatings= new ArrayList<>();

}
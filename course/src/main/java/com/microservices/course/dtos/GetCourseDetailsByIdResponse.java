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
public class GetCourseDetailsByIdResponse {
    private Long courseId;
    private String courseName;
    private String domain;
    private String courseDescription;
    private String courseAuthor;
    private Double coursePrice;
    private Double courseDuration;
    private List<Review> courseReviews = new ArrayList<>();
    private List<Rating> courseRatings = new ArrayList<>();
}

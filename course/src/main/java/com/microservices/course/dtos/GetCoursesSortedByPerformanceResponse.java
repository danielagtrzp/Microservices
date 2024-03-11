package com.microservices.course.dtos;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class GetCoursesSortedByPerformanceResponse {

    private Long courseId;
    private String courseName;
    private String courseDescription;
    private String courseAuthor;
    private String domain;
    private Double coursePrice;
    private Double courseDuration;
    private Double performance;

}
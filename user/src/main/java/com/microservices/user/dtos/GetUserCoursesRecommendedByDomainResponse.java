package com.microservices.user.dtos;


import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class GetUserCoursesRecommendedByDomainResponse {

    private Long courseId;
    private String courseName;
    private String courseDescription;
    private String courseAuthor;
    private String domain;
    private Double coursePrice;
    private Double courseDuration;
    private Double performance;

}

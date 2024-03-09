package com.microservices.course.dtos;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class AddCourseResponse {

    private Long courseId;
    private String courseName;
    private String courseDescription;
    private String courseAuthor;
    private Double coursePrice;
    private Double courseDuration;

}

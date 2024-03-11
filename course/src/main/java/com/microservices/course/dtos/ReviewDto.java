package com.microservices.course.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class ReviewDto {
    private Long reviewId;
    private String review;
}
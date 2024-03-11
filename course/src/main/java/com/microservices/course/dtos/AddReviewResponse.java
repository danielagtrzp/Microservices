package com.microservices.course.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class AddReviewResponse {
    private Long reviewId;
    private String review;
    private Long user;
}

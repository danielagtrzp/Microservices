package com.microservices.course.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class RatingDto {
    private Long ratingId;
    private Double rating;
}
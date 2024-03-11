package com.microservices.cart.dtos;


import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class CartCourseFeignResponse {

    private Long courseId;
    private String courseName;
    private Double coursePrice;

}

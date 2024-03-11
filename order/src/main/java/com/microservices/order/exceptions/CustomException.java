package com.microservices.order.exceptions;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomException {
    private LocalDate timeStamp;
    private String message;
    private HttpStatus status;
    private String path;
}
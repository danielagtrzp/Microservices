package com.microservices.user.exception;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;

import lombok.*;

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
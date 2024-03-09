package com.microservices.course.exceptions;

public class GeneralCustomException extends Exception{
    public GeneralCustomException() {
    }

    public GeneralCustomException(String message) {
        super(message);
    }
}

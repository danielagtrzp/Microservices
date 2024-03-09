package com.microservices.user.exception;

public class GeneralCustomException extends Exception{
    public GeneralCustomException() {
    }

    public GeneralCustomException(String message) {
        super(message);
    }
}

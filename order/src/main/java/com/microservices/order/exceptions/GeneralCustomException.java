package com.microservices.order.exceptions;

public class GeneralCustomException extends Exception{
    public GeneralCustomException() {
    }

    public GeneralCustomException(String message) {
        super(message);
    }
}

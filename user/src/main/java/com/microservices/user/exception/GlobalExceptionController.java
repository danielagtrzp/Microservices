package com.microservices.user.exception;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.TransactionRequiredException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDate;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.resource.NoResourceFoundException;
@RestControllerAdvice
public class GlobalExceptionController {

    //SAVE
    @ExceptionHandler(EntityExistsException.class)
    @ResponseStatus(code = HttpStatus.CONFLICT)
    public CustomException handlerEntityExistsException(EntityExistsException ex, WebRequest request){

        CustomException response = new CustomException().builder()
                .timeStamp(LocalDate.now())
                .message(ex.getMessage())
                .status(HttpStatus.CONFLICT)
                .path(((ServletWebRequest)request).getRequest().getRequestURI())
                .build();
        return response;
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
    public CustomException handlerDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request){

        CustomException response = new CustomException().builder()
                .timeStamp(LocalDate.now())
                .message(ex.getMessage())
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .path(((ServletWebRequest)request).getRequest().getRequestURI())
                .build();
        return response;
    }

    @ExceptionHandler(TransactionRequiredException.class)
    @ResponseStatus(code = HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public CustomException handlerTransactionRequiredException(TransactionRequiredException ex, WebRequest request){

        CustomException response = new CustomException().builder()
                .timeStamp(LocalDate.now())
                .message(ex.getMessage())
                .status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                .path(((ServletWebRequest)request).getRequest().getRequestURI())
                .build();
        return response;
    }

    @ExceptionHandler(JpaSystemException.class)
    @ResponseStatus(code = HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public CustomException handlerJpaSystemException(JpaSystemException ex, WebRequest request){

        CustomException response = new CustomException().builder()
                .timeStamp(LocalDate.now())
                .message(ex.getMessage())
                .status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                .path(((ServletWebRequest)request).getRequest().getRequestURI())
                .build();
        return response;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public CustomException handlerIllegalArgumentException(IllegalArgumentException ex, WebRequest request){

        CustomException response = new CustomException().builder()
                .timeStamp(LocalDate.now())
                .message(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST)
                .path(((ServletWebRequest)request).getRequest().getRequestURI())
                .build();
        return response;
    }

    //

    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public CustomException handlerResourceNotFoundException(NoResourceFoundException ex, WebRequest request){

        CustomException response = new CustomException().builder()
                .timeStamp(LocalDate.now())
                .message(ex.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .path(((ServletWebRequest)request).getRequest().getRequestURI())
                .build();
        return response;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public CustomException handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request){

       String  errorMessages = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.toList())
               .toString();

        CustomException response = new CustomException().builder()
                .timeStamp(LocalDate.now())
                .message(errorMessages)
                .status(HttpStatus.BAD_REQUEST)
                .path(((ServletWebRequest)request).getRequest().getRequestURI())
                .build();
        return response;
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED)
    public CustomException handlerHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex, WebRequest request){

        CustomException response = new CustomException().builder()
                .timeStamp(LocalDate.now())
                .message(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST)
                .path(((ServletWebRequest)request).getRequest().getRequestURI())
                .build();
        return response;
    }
}

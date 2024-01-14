package com.springboot.studentManagement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class globalExceptionHandler {
    @ExceptionHandler(resourceNotFoundException.class)
    public ResponseEntity<?> studentNOtFoundException(resourceNotFoundException ex, WebRequest request){
        errorDetails errorDetails=new errorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
}

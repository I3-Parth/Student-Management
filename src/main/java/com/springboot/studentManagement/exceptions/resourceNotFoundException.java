package com.springboot.studentManagement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class resourceNotFoundException extends Exception{
    public resourceNotFoundException(Long id){
        super("404 not found with id: "+id);
    }
}

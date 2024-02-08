package com.springboot.studentManagement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class InvalidAddressException extends Exception{
    public InvalidAddressException(Long id){
        super("Invalid AddressId "+ id +" provided");
    }
}

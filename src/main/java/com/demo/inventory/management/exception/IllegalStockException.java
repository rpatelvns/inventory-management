package com.demo.inventory.management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class IllegalStockException extends RuntimeException{
    public IllegalStockException(String message){
        super(message);
    }
}
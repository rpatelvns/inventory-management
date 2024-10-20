package com.demo.inventory.management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class OutOfStockException extends RuntimeException{
    public OutOfStockException(String message){
        super(message);
    }
}
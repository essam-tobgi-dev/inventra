package com.chainlink.exceptions;

import java.util.Date;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponse> handleElementNotFound(NoSuchElementException ex){
        return new ResponseEntity<>(
            new ErrorResponse(
                new Date(),
                HttpStatus.NOT_FOUND.value(),
                "Element Not Found!",
                ex.getMessage()),
            HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(OutOfStockException.class)
    public ResponseEntity<ErrorResponse> handleOutOfStockException(OutOfStockException ex){
        return new ResponseEntity<>(
            new ErrorResponse(
                new Date(),
                HttpStatus.NOT_FOUND.value(),
                "Out of stock exception!",
                ex.getMessage()),
            HttpStatus.NOT_FOUND
        );
    }
}

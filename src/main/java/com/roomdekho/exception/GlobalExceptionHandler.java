package com.roomdekho.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<String> handleEmailException(
            EmailAlreadyExistsException exception
    ){

        return new ResponseEntity<>(
                exception.getMessage(),
                HttpStatus.BAD_REQUEST
        );

    }

}
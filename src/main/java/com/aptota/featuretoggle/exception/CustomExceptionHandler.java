package com.aptota.featuretoggle.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleUserNotFoundException(UsernameNotFoundException exception){
        ErrorMessage errorMessage = ErrorMessage.builder()
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.NOT_FOUND.value())
                .status(HttpStatus.NOT_FOUND.toString())
                .message(exception.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleUserNotFoundException(Exception exception){
        ErrorMessage errorMessage = ErrorMessage.builder()
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                .message(exception.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }

}

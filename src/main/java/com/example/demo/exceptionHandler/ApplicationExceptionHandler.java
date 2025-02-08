package com.example.demo.exceptionHandler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.reponse.ResponseHandler;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleInvalidArgument(MethodArgumentNotValidException e) {
        Map<String, String> errorMap = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });

        return ResponseHandler.responseBuilder(
            errorMap, 
            null, 
            HttpStatus.NOT_ACCEPTABLE
        );
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleIntegrationViolation(DataIntegrityViolationException e) {
        return ResponseHandler.responseBuilder(
            Map.of("message", e.getMessage()),
            "Please use different email address", 
            HttpStatus.NOT_ACCEPTABLE
        );
    }

}

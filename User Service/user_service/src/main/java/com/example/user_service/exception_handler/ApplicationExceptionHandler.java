package com.example.user_service.exception_handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.user_service.exceptions.UserAlreadyExistsException;
import com.example.user_service.handler.ResponseHandler;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException e) {
        Map<String, String> errorMap = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });

        e.getBindingResult().getGlobalErrors().forEach(error -> {
            errorMap.put(error.getObjectName(), error.getDefaultMessage());
        });
        return errorMap;
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleSQLIntegrityConstraintViolationException(DataIntegrityViolationException e) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", e.getMostSpecificCause().getMessage());
        return ResponseHandler.responseBuilder(errorMap, "Used different email for registration", HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<?> handleGenericException(UserAlreadyExistsException e) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", e.getMessage());
        return ResponseHandler.responseBuilder(errorMap, "Used different email for registration", HttpStatus.BAD_REQUEST);
    }  

}

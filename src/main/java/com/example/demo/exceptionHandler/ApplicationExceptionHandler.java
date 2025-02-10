package com.example.demo.exceptionHandler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.exceptions.PasswordAndConfirmPasswordFieldIsNotMatchingException;
import com.example.demo.exceptions.UserNotRegisteredException;
import com.example.demo.reponse.ResponseHandler;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleInvalidArgument(MethodArgumentNotValidException e) {
        Map<String, String> errorMap = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });

        return ResponseHandler.suggesionBuilder(
            errorMap, 
            "Please use valid email address", 
            HttpStatus.NOT_ACCEPTABLE
        );
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleIntegrationViolation(DataIntegrityViolationException e) {
        return ResponseHandler.suggesionBuilder(
            Map.of("message", e.getMessage()),
            "Please use different email address", 
            HttpStatus.NOT_ACCEPTABLE
        );
    }


    @ExceptionHandler(PasswordAndConfirmPasswordFieldIsNotMatchingException.class)
    public ResponseEntity<?> handlePasswordAndConfirmPasswordFieldIsNotMatchingException(PasswordAndConfirmPasswordFieldIsNotMatchingException e) {
        return ResponseHandler.suggesionBuilder(
            Map.of("message", e.getMessage()), 
            "Please type same password in both fields", 
            HttpStatus.BAD_REQUEST
        );
    }


    @ExceptionHandler(UserNotRegisteredException.class)
    public ResponseEntity<?> handleUserNotRegisteredException(UserNotRegisteredException e) {
        return ResponseHandler.suggesionBuilder(
            null, 
            e.getMessage(), 
            HttpStatus.NOT_FOUND
        );
    }

}

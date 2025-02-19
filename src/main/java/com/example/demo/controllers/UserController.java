package com.example.demo.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.LogInDTO;
import com.example.demo.dtos.SignUpDTO;
import com.example.demo.exceptions.PasswordAndConfirmPasswordFieldIsNotMatchingException;
import com.example.demo.exceptions.UserNotRegisteredException;
import com.example.demo.reponse.ResponseHandler;
import com.example.demo.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/message")
    public ResponseEntity<?> getMessage() {
        return ResponseHandler.responseBuilder(
            null, 
            "Welcome to Smart Expense Manager", 
            HttpStatus.OK
            
        );
    }


    @PostMapping("/sign-up")
    public ResponseEntity<?> registeredUser(@RequestBody @Valid SignUpDTO userData) throws PasswordAndConfirmPasswordFieldIsNotMatchingException {
        userService.saveUserData(userData);
        return ResponseHandler.responseBuilder(userData, "You are registered succesfully", HttpStatus.CREATED);
    }


    @PostMapping("/log-in")
    public ResponseEntity<?> verifyUser(@RequestBody @Valid LogInDTO userCredentials) throws UserNotRegisteredException {
        String token = userService.verifyUser(userCredentials);
        return ResponseHandler.responseBuilder(
            Map.of("token", token), 
            "You are succesfully log-in to the application", 
            HttpStatus.OK
        );
    }

}

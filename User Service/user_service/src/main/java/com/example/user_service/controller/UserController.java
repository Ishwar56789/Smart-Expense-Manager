package com.example.user_service.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.user_service.dto.LogInDTO;
import com.example.user_service.dto.SignUpDTO;
import com.example.user_service.exceptions.UserAlreadyExistsException;
import com.example.user_service.exceptions.UserNotRegisteredException;
import com.example.user_service.handler.ResponseHandler;
import com.example.user_service.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/message")
    public ResponseEntity<?> message() {
        return ResponseHandler.responseBuilder(null, "This is a testing API from SEM User Service", HttpStatus.OK);
    }


    @PostMapping("/sign-up")
    public ResponseEntity<?> userRegistration(@Valid @RequestBody SignUpDTO userData) throws UserAlreadyExistsException {
        userService.saveUserData(userData);
        return ResponseHandler.responseBuilder(null, "User registered succesfully", HttpStatus.OK);
    }


    @PostMapping("/log-in")
    public ResponseEntity<?> userLogIn(@RequestBody @Valid LogInDTO userCredentials) throws UserNotRegisteredException {
        String jwtToken = userService.verifyUserLogIn(userCredentials);
        return ResponseHandler.responseBuilder(
            Map.of("jwtToken", jwtToken),
            "User logged in succesfully", 
            HttpStatus.OK
        );
    }

}

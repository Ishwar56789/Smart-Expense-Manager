package com.example.user_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.user_service.dto.SignUpDto;
import com.example.user_service.handler.ResponseHandler;
import com.example.user_service.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<?> userRegistration(@Valid @RequestBody SignUpDto userData) {
        userService.saveUserData(userData);
        return ResponseHandler.responseBuilder(null, "User registered succesfully", HttpStatus.OK);
    }

}

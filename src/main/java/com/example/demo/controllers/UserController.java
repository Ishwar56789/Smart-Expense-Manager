package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.SignUpDTO;
import com.example.demo.reponse.ResponseHandler;
import com.example.demo.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/message")
    public String getMessage() {
        return "Welcome to Smart Expense Manager";
    }


    @PostMapping("/sign-up")
    public ResponseEntity<?> registeredUser(@RequestBody @Valid SignUpDTO userData) {
        userService.saveUSerData(userData);
        return ResponseHandler.responseBuilder(userData, "You are registered succesfully", HttpStatus.CREATED);
    }

}

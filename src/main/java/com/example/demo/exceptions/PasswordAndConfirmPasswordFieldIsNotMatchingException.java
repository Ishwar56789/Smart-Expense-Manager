package com.example.demo.exceptions;

public class PasswordAndConfirmPasswordFieldIsNotMatchingException extends Exception {

    public PasswordAndConfirmPasswordFieldIsNotMatchingException(String message) {
        super(message);
    }

}

package com.example.demo.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDTO {

    @NotEmpty(message = "Name is important for registration")
    private String fullName;

    @Email(message = "Invalid Email format")
    private String emailAddress;

    @NotEmpty(message = "Password is important for registration")
    private String password;

    @NotEmpty(message = "Confirm password filled is empty")
    private String confirmPassword;

    private String currencyPrefrence;

}

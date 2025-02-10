package com.example.demo.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogInDTO {

    @Email(message = "Invalid email address")
    @NotEmpty(message = "Email address is necessary for log-in")
    private String registeredUserEmail;

    @NotEmpty(message = "Password is necessary for log-in")
    private String registeredUserPassword;

}

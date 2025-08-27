package com.example.user_service.dto;

import com.example.user_service.custom_validation.PasswordMatches;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@PasswordMatches
public class SignUpDTO {

    @NotBlank(message = "Username cannot be blank")
    @Size(min = 3, max = 30, message = "Username must be between 3 and 30 characters")
    private String userName;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    private String userEmail;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
    @Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
        message = "Password must have at least 1 uppercase letter, 1 lowercase letter, 1 digit, and 1 special character"
    )
    private String userPassword;

    @NotBlank(message = "Confirm password cannot be blank")
    private String confirmUserPassword;

    @NotBlank(message = "Currency preference cannot be blank")
    @Pattern(
        regexp = "^[A-Z]{3}$",
        message = "Currency preference must be a valid 3-letter ISO code (e.g., USD, INR, EUR)"
    )
    private String userCurrencyPrefrence;

}

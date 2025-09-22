package com.example.user_service.custom_validator;

import com.example.user_service.custom_validation.PasswordMatches;
import com.example.user_service.dto.SignUpDTO;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, SignUpDTO>{

    @Override
    public boolean isValid(SignUpDTO dto, ConstraintValidatorContext context) {
        return dto.getUserPassword().equals(dto.getConfirmUserPassword());
    }

}

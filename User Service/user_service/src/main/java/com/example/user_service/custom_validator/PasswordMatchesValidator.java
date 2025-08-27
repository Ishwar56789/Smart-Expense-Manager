package com.example.user_service.custom_validator;

import com.example.user_service.custom_validation.PasswordMatches;
import com.example.user_service.dto.SignUpDto;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, SignUpDto>{

    @Override
    public boolean isValid(SignUpDto dto, ConstraintValidatorContext context) {
        return dto.getUserPassword().equals(dto.getConfirmUserPassword());
    }

}

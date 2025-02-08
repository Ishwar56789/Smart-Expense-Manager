package com.example.demo.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.SignUpDTO;
import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder; 

    private User dtoToEntity(SignUpDTO userData) {
        User user = new User();
        user.setUserName(userData.getFullName());
        user.setUserEmail(userData.getEmailAddress());
        user.setUserPassword(passwordEncoder.encode(userData.getConfirmPassword()));
        user.setUserCurrencyPrefrence(userData.getCurrencyPrefrence());
        user.setUserAccountCreatinDate(LocalDate.now());
        return user;
    }

    public void saveUSerData(SignUpDTO userData) {
        if (userRepository.findByEmailAddress(userData.getEmailAddress()).isPresent()) {
            throw new DataIntegrityViolationException("Email address is already registred");
        }

        User user = dtoToEntity(userData);
        userRepository.save(user);
        emailService.registrationMailMessage(
            userData.getEmailAddress(), 
            "Registration Mail", 
            "You are registered succesfully"
        );
    }

}

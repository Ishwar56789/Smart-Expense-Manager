package com.example.demo.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.LogInDTO;
import com.example.demo.dtos.SignUpDTO;
import com.example.demo.exceptions.PasswordAndConfirmPasswordFieldIsNotMatchingException;
import com.example.demo.exceptions.UserNotRegisteredException;
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

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthService authService;

    private User dtoToEntity(SignUpDTO userData) {
        User user = new User();
        user.setUserName(userData.getFullName());
        user.setUserEmail(userData.getEmailAddress());
        user.setUserPassword(passwordEncoder.encode(userData.getConfirmPassword()));
        user.setUserCurrencyPrefrence(userData.getCurrencyPrefrence());
        user.setUserAccountCreatinDate(LocalDate.now());
        return user;
    }

    public void saveUserData(SignUpDTO userData) throws PasswordAndConfirmPasswordFieldIsNotMatchingException {
        if (userRepository.findByEmailAddress(userData.getEmailAddress()).isPresent()) {
            throw new DataIntegrityViolationException("Email address is already registred");
        }

        if (!userData.getPassword().equals(userData.getConfirmPassword())) {
            throw new PasswordAndConfirmPasswordFieldIsNotMatchingException("Password and Confirm password field is not matching");
        }

        User user = dtoToEntity(userData);
        userRepository.save(user);
        emailService.registrationMailMessage(
            userData.getEmailAddress(), 
            "Registration Mail", 
            "You are registered succesfully"
        );
    }

    public String verifyUser(LogInDTO userCredentials) throws UserNotRegisteredException {
        if (userRepository.findByEmailAddress(userCredentials.getRegisteredUserEmail()).isEmpty()) {
            throw new UserNotRegisteredException("You have to registered before trying to log-in");
        }

        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                userCredentials.getRegisteredUserEmail(), 
                userCredentials.getRegisteredUserPassword()
            ) 
        );
        long userId = userRepository.findUserIdByEmail(userCredentials.getRegisteredUserEmail());
        return authService.generateToken(userCredentials.getRegisteredUserEmail(), userId);
    }

}

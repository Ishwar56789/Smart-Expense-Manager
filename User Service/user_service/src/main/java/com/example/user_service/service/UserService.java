package com.example.user_service.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.user_service.dto.LogInDTO;
import com.example.user_service.dto.SignUpDTO;
import com.example.user_service.exceptions.UserAlreadyExistsException;
import com.example.user_service.exceptions.UserNotRegisteredException;
import com.example.user_service.model.User;
import com.example.user_service.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;
    

    private User dtoToEntity(SignUpDTO userData) {
        User user = new User();
        user.setUserName(userData.getUserName());
        user.setUserEmail(userData.getUserEmail());
        user.setUserPassword(bCryptPasswordEncoder.encode(userData.getUserPassword()));
        user.setUserCurrencyPrefrence(userData.getUserCurrencyPrefrence());
        user.setUserAccountCreatedAt(LocalDate.now());
        // user.setUserAccountLastUpdatedAt(null);
        return user;
    }

    public void saveUserData(SignUpDTO userData) throws UserAlreadyExistsException {
        if (userRepository.existsByUserEmail(userData.getUserEmail())) {
            throw new UserAlreadyExistsException("User with email " + userData.getUserEmail() + " already exists");
        }

        User user = dtoToEntity(userData);
        userRepository.save(user);
    }


    public String verifyUserLogIn(LogInDTO userCredentials) throws UserNotRegisteredException {
        if (!userRepository.existsByUserEmail(userCredentials.getRegisteredEmail())) {
            throw new UserNotRegisteredException("User not registered with email " + userCredentials.getRegisteredEmail());
        }

        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    userCredentials.getRegisteredEmail(), 
                    userCredentials.getRegisteredPassword()
                )
            );
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect password for email " + userCredentials.getRegisteredEmail());
        }

        Long userId = userRepository.findByUserEmail(userCredentials.getRegisteredEmail()).get().getId();
        return "Successfully logged in! User ID: " + userId;
    }

}

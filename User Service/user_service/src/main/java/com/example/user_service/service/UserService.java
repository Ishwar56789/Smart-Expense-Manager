package com.example.user_service.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.user_service.dto.SignUpDTO;
import com.example.user_service.model.User;
import com.example.user_service.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    

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

    public void saveUserData(SignUpDTO userData) {
        User user = dtoToEntity(userData);
        userRepository.save(user);
    }

}

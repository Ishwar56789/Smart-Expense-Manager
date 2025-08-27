package com.example.user_service.handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.user_service.dto.ResponseDTO;

public class ResponseHandler {

    public static ResponseEntity<?> responseBuilder(Object responseData, String message, HttpStatus status) {
        ResponseDTO responseDTO = new ResponseDTO(
            LocalDateTime.now(),
            message, 
            status.value(),
            responseData
        );
        return new ResponseEntity<>(responseDTO, status);
    }

}

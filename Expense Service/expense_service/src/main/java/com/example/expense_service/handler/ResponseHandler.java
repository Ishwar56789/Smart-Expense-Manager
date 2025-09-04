package com.example.expense_service.handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.expense_service.dtos.ResponseDTO;

public class ResponseHandler {

    public static ResponseEntity<?> resposeBuilder(Object resposeData, String message, HttpStatus status) {
        ResponseDTO responseDTO = new ResponseDTO(
            LocalDateTime.now(),
            message,
            status.value(),
            resposeData
        );
        return new ResponseEntity<>(responseDTO, status);
    }

}

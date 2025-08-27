package com.example.user_service.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {

    private LocalDateTime timeStamp;
    
    private String message;
    
    private int status;
    
    private Object data;

}

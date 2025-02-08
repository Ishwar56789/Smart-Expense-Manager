package com.example.demo.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor 
@NoArgsConstructor
public class SuggesionDTO {

    private LocalDateTime timeStamp;
    
    private String suggesion;
    
    private int status;
    
    private Object data;

}

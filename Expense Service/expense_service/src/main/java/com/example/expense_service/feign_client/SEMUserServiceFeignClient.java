package com.example.expense_service.feign_client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "SEM-USER-SERVICE", path = "/api/v1/user")
public interface SEMUserServiceFeignClient {

    @GetMapping("/message")
    public ResponseEntity<?> messageAPIFromUserService(@RequestHeader("Authorization") String authHeader);

}

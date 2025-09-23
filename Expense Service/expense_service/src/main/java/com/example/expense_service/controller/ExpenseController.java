package com.example.expense_service.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.expense_service.dtos.AddExpenseDTO;
import com.example.expense_service.feign_client.SEMUserServiceFeignClient;
import com.example.expense_service.handler.ResponseHandler;
import com.example.expense_service.services.ExpenseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/expense")
public class ExpenseController {

    @Autowired 
    private ExpenseService expenseService;

    @Autowired
    private SEMUserServiceFeignClient userServiceFeignClient;

    @PostMapping("/add-expense")
    public ResponseEntity<?> addExpense(@RequestBody @Valid AddExpenseDTO expenseData, @RequestHeader("Authorization") String authHeader) {
        expenseService.saveExpense(expenseData, authHeader);
        return ResponseHandler.resposeBuilder(null, "Expense added successfully", HttpStatus.CREATED);
    }


    @GetMapping("/all-expenses")
    public ResponseEntity<?> getAllExpenses(@RequestHeader("Authorization") String authHeader) {
        return ResponseHandler.resposeBuilder(
            expenseService.getAllExpenses(authHeader), "All expenses fetched successfully till " + LocalDate.now(), HttpStatus.OK
        );
    }


    @GetMapping("/message")
    public ResponseEntity<?> message(@RequestHeader("Authorization") String authHeader) {
        return userServiceFeignClient.messageAPIFromUserService(authHeader);
    }

}

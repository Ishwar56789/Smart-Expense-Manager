package com.example.expense_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.expense_service.dtos.AddExpenseDTO;
import com.example.expense_service.handler.ResponseHandler;
import com.example.expense_service.services.ExpenseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/expense")
public class ExpenseController {

    @Autowired 
    private ExpenseService expenseService;

    @PostMapping("/add-expense")
    public ResponseEntity<?> addExpense(@RequestBody @Valid AddExpenseDTO expenseData) {
        expenseService.saveExpense(expenseData);
        return ResponseHandler.resposeBuilder(null, "Expense added successfully", HttpStatus.CREATED);
    }

}

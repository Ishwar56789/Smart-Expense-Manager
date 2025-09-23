package com.example.expense_service.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.expense_service.dtos.AddExpenseDTO;
import com.example.expense_service.dtos.ExpenseDTO;
import com.example.expense_service.filter.JWTFilter;
import com.example.expense_service.model.Expense;
import com.example.expense_service.repository.ExpenseRepository;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private JWTFilter jwtFilter;


    private Expense dtoToExpense(AddExpenseDTO expenseData, Long userID) {
        Expense expense = new Expense();
        expense.setExpenseTitle(expenseData.getExpenseTitle());
        expense.setExpenseAmount(expenseData.getExpenseAmount());
        expense.setExpenseDescription(expenseData.getExpenseDescription());
        expense.setExpenseSpendingDate(expenseData.getExpenseSpendingDate());
        expense.setExpenseCategory(expenseData.getExpenseCategory());
        expense.setExpensePaymentMethod(expenseData.getExpensePaymentMethod());
        expense.setExpenseRegistrationDate(LocalDate.now());
        expense.setUserId(userID); // Placeholder for user ID, to be replaced with actual user context
        return expense;
    }

    public void saveExpense(AddExpenseDTO expenseData, String authHeader) {
        Long userID = jwtFilter.extractUserID(authHeader);

        Expense expense = dtoToExpense(expenseData, userID);
        expenseRepository.save(expense);
    }


    private ExpenseDTO expenseToDTO(Expense expenseData) {
        return new ExpenseDTO(
            expenseData.getExpenseTitle(),
            expenseData.getExpenseAmount(),
            expenseData.getExpenseDescription(),
            expenseData.getExpenseSpendingDate(),
            expenseData.getExpenseCategory(),
            expenseData.getExpensePaymentMethod(),
            expenseData.getExpenseRegistrationDate(),
            expenseData.getExpenseUpdateDate()
        );
    }

    public Object getAllExpenses(String authHeader) {
        Long userID = jwtFilter.extractUserID(authHeader);

        List<Expense> expenseData = expenseRepository.findByUserId(userID);

        return expenseData.stream().map(this::expenseToDTO).toList();
    }

}

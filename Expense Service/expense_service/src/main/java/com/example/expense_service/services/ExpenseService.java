package com.example.expense_service.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.expense_service.dtos.AddExpenseDTO;
import com.example.expense_service.model.Expense;
import com.example.expense_service.repository.ExpenseRepository;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    private Expense dtoToExpense(AddExpenseDTO expenseData) {
        Expense expense = new Expense();
        expense.setExpenseTitle(expenseData.getExpenseTitle());
        expense.setExpenseAmount(expenseData.getExpenseAmount());
        expense.setExpenseDescription(expenseData.getExpenseDescription());
        expense.setExpenseSpendingDate(expenseData.getExpenseSpendingDate());
        expense.setExpenseCategory(expenseData.getExpenseCategory());
        expense.setExpensePaymentMethod(expenseData.getExpensePaymentMethod());
        expense.setExpenseRegistrationDate(LocalDate.now());
        expense.setUserId(1L); // Placeholder for user ID, to be replaced with actual user context
        return expense;
    }

    public void saveExpense(AddExpenseDTO expenseData) {
        Expense expense = dtoToExpense(expenseData);
        expenseRepository.save(expense);
    }

}

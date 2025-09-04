package com.example.expense_service.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddExpenseDTO {

    @NotBlank(message = "Expense title is required")
    @Size(max = 100, message = "Expense title must not exceed 100 characters")
    private String expenseTitle;

    @NotNull(message = "Expense amount is required")
    @DecimalMin(value = "0.01", inclusive = true, message = "Expense amount must be greater than 0")
    private BigDecimal expenseAmount;

    @Size(max = 255, message = "Expense description must not exceed 255 characters")
    private String expenseDescription;

    @NotNull(message = "Spending date is required")
    @PastOrPresent(message = "Spending date cannot be in the future")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate expenseSpendingDate;

    @NotBlank(message = "Expense category is required")
    private String expenseCategory;

    @NotBlank(message = "Payment method is required")
    private String expensePaymentMethod;

}

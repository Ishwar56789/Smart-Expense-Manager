package com.example.expense_service.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "expense_data_table")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "expense_title")
    private String expenseTitle;

    @Column(name = "expense_spending_amount")
    private BigDecimal expenseAmount;

    @Column(name = "expense_description")
    private String expenseDescription;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "expense_spending_date")
    private LocalDate expenseSpendingDate;

    @Column(name = "expense_category")
    private String expenseCategory;

    @Column(name = "payment_method")
    private String  expensePaymentMethod;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name = "expense_registered_date")
    private LocalDate expenseRegistrationDate;

    @Column(name = "user_given_id")
    private Long userId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name = "last_expense_updated_date")
    private LocalDate expenseUpdateDate;

}

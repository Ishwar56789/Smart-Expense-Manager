package com.example.expense_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.expense_service.model.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

}

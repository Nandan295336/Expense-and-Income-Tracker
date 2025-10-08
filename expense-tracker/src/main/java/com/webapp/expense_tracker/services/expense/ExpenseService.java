package com.webapp.expense_tracker.services.expense;

import com.webapp.expense_tracker.dto.ExpenseDTO;
import com.webapp.expense_tracker.entity.Expense;

import java.util.List;

public interface ExpenseService {
    Expense postExpense(ExpenseDTO expenseDTO);

    List<Expense> getAllExpenses();

    Expense getExpenseById(Long id);

    Expense updateExpense(Long id, ExpenseDTO dto);

    void deleteExpense(Long id);
}

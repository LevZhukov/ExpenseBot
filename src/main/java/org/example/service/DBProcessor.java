package org.example.service;

import org.example.expense.Expense;
import org.example.expense.ExpenseRepository;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalDate;

@Service
public class DBProcessor {
    private ExpenseRepository expenseRepository;

    public DBProcessor(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public Integer addRecord(Update update) {
        double sum;
        try {
            sum = Double.parseDouble(update.getMessage().getText().toString());
        } catch (NumberFormatException e) {
            return -1;
        }
        Expense expense = new Expense();
        expense.setSum(sum);
        expense.setDate(LocalDate.now());
        expense.setUsr(update.getMessage().getChat().getFirstName());
        expenseRepository.save(expense);
        return expense.getId();
    }
}

package org.example.service;

import lombok.extern.log4j.Log4j;
import org.example.expense.Expense;
import org.example.expense.ExpenseRepository;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Comparator;

@Service
@Log4j
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
    @Transactional
    public Boolean categoryUpdated(Integer id, String category){
        try{
            Expense expense = expenseRepository.findById(id).get();
            expense.setCategory(category);
            log.debug(category);
            return true;
        }
        catch (Exception e){
            log.error(e.getMessage());
        }
            return false;
    }
    public String showAll() {
        String allRecords = expenseRepository.findAll().stream()
                .sorted(Comparator.comparing(Expense::getId))
                .reduce("", (s, expense) -> s + expense.print(),
                        (s, s2) -> s.concat(s2));
        return allRecords;
    }
}

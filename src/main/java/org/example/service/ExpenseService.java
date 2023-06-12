package org.example.service;

import lombok.extern.log4j.Log4j;
import org.example.expense.ExpenseRepository;
import org.springframework.stereotype.Service;

@Service
@Log4j
public class ExpenseService {
    private ExpenseRepository expenseRepository;
}

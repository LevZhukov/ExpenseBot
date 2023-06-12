package org.example.expense;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.time.LocalDate;

@Entity
@Data
public class Expense {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;
    private Double sum;
    private LocalDate date;
    private String category;
    private String usr;

    public String print() {
        return "№" + Id + " " +
                date + " " +
                sum.longValue() + " " +
                category + '\n';
    }
}
package com.webapp.expense_tracker.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ExpenseDTO {

    private Long id;

    private String description;

    private String title;

    private String category;

    private LocalDate date;

    private Integer amount;
}

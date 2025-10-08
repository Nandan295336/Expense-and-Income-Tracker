package com.webapp.expense_tracker.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class IncomeDTO {

    private Long id;

    private String title;

    private LocalDate date;

    private String description;

    private Integer amount;

    private String category;
}

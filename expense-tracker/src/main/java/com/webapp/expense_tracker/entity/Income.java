package com.webapp.expense_tracker.entity;

import com.webapp.expense_tracker.dto.IncomeDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private LocalDate date;

    private String description;

    private Integer amount;

    private String category;

    public IncomeDTO getIncomeDto()
    {
        IncomeDTO incomeDTO = new IncomeDTO();

        incomeDTO.setId(id);
        incomeDTO.setDate(date);
        incomeDTO.setAmount(amount);
        incomeDTO.setTitle(title);
        incomeDTO.setCategory(category);
        incomeDTO.setDescription(description);

        return incomeDTO;
    }

}

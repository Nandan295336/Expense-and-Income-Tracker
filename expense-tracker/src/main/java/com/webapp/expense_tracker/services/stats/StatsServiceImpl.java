package com.webapp.expense_tracker.services.stats;

import com.webapp.expense_tracker.dto.GraphDTO;
import com.webapp.expense_tracker.dto.StatsDTO;
import com.webapp.expense_tracker.entity.Expense;
import com.webapp.expense_tracker.entity.Income;
import com.webapp.expense_tracker.repository.ExpenseRepository;
import com.webapp.expense_tracker.repository.IncomeRepositary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

@Service
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService {
    private final IncomeRepositary incomeRepositary;

    private final ExpenseRepository expenseRepository;

    public GraphDTO getChartData()
    {
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(27);

        GraphDTO graphDTO = new GraphDTO();
        graphDTO.setExpenseList(expenseRepository.findByDateBetween(startDate,endDate));
        graphDTO.setIncomeList(incomeRepositary.findByDateBetween(startDate,endDate));

        return graphDTO;
    }

    public StatsDTO getStats()
    {
        Double totalIncome = incomeRepositary.sumALlAmounts();
        Double totalExpense = expenseRepository.sumALlAmounts();

        Optional<Income> optionalIncome = incomeRepositary.findFirstByOrderByDateDesc();
        Optional<Expense> optionalExpense = expenseRepository.findFirstByOrderByDateDesc();

        StatsDTO statsDTO = new StatsDTO();
        statsDTO.setExpense(totalExpense);
        statsDTO.setIncome(totalIncome);
        statsDTO.setBalance(totalIncome-totalExpense);

        List<Income> incomeList = incomeRepositary.findAll();
        List<Expense> expenseList = expenseRepository.findAll();

        OptionalDouble minIncome = incomeList.stream().mapToDouble(Income::getAmount).min();
        OptionalDouble maxIncome = incomeList.stream().mapToDouble(Income::getAmount).max();

        OptionalDouble minExpense = expenseList.stream().mapToDouble(Expense::getAmount).min();
        OptionalDouble maxExpense = expenseList.stream().mapToDouble(Expense::getAmount).max();

        optionalIncome.ifPresent(statsDTO::setLatestIncome);
        optionalExpense.ifPresent(statsDTO::setLatestExpense);

        statsDTO.setMaxExpense(maxExpense.isPresent()? maxExpense.getAsDouble() : null);
        statsDTO.setMinExpense(minExpense.isPresent()? minExpense.getAsDouble() : null);

        statsDTO.setMaxIncome(maxIncome.isPresent()? maxIncome.getAsDouble() : null);
        statsDTO.setMinIncome(minIncome.isPresent()? minIncome.getAsDouble() : null);
        return statsDTO;
    }
}

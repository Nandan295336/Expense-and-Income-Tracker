package com.webapp.expense_tracker.services.income;

import com.webapp.expense_tracker.dto.IncomeDTO;
import com.webapp.expense_tracker.entity.Income;
import com.webapp.expense_tracker.repository.IncomeRepositary;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IncomeServiceImpl implements IncomeService {

    private final IncomeRepositary incomeRepositary;

    public Income postIncome(IncomeDTO incomeDTO)
    {
        return saveOrUpdateIncome(new Income(), incomeDTO);
    }

    private Income saveOrUpdateIncome(Income income, IncomeDTO incomeDTO)
    {
        income.setAmount(incomeDTO.getAmount());
        income.setDate(incomeDTO.getDate());
        income.setCategory(incomeDTO.getCategory());
        income.setTitle(incomeDTO.getTitle());
        income.setDescription(incomeDTO.getDescription());

        return incomeRepositary.save(income);
    }

    public Income updateIncome(Long id, IncomeDTO incomeDTO)
    {
        Optional<Income> optionalIncome = incomeRepositary.findById(id);
        if(optionalIncome.isPresent()){
            return saveOrUpdateIncome(optionalIncome.get(), incomeDTO);
        }
        else {
            throw new EntityNotFoundException("Entity not present with id "+id );
        }
    }

    public List<IncomeDTO> getAllIncomes(){
        return incomeRepositary.findAll().stream()
                .sorted(Comparator.comparing(Income::getDate).reversed())
                .map(Income::getIncomeDto)
                .collect(Collectors.toList());
    }

    public IncomeDTO getIncomeById(Long id)
    {
        Optional<Income> optionalIncome = incomeRepositary.findById(id);
        if(optionalIncome.isPresent())
        {
            return optionalIncome.get().getIncomeDto();
        }
        else {
            throw new EntityNotFoundException("Entity not found with id "+id);
        }
    }

    public void deleteIncome(Long id)
    {
        Optional<Income> optionalIncome = incomeRepositary.findById(id);
        if(optionalIncome.isPresent())
        {
            incomeRepositary.deleteById(id);
        }
        else {
            throw new EntityNotFoundException("Entity not present with id "+id);
        }
    }

}

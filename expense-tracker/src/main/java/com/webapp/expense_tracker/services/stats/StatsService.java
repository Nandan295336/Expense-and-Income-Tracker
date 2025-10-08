package com.webapp.expense_tracker.services.stats;

import com.webapp.expense_tracker.dto.GraphDTO;
import com.webapp.expense_tracker.dto.StatsDTO;

public interface StatsService {

    GraphDTO getChartData();

    StatsDTO getStats();
}

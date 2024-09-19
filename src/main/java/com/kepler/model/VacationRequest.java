package com.kepler.model;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class VacationRequest {
    private double averageSalary;
    private int vacationDays;
    private LocalDate startDate;
    private LocalDate endDate;
}

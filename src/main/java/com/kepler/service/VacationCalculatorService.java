package com.kepler.service;

import com.kepler.model.VacationRequest;
import com.kepler.proxy.DayOffProxy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.stream.Collectors;

@Service
@Slf4j
public class VacationCalculatorService {
    @Autowired
    private DayOffProxy dayOffProxy;

    /**
     * @param request salary, days, start / end vacation
     * @return cash for vacation
     */
    public double calculateVacationPay(VacationRequest request) {
        double dailySalary = request.getAverageSalary() / 29.3; // average salary
        int totalVacationDays = request.getVacationDays();

        if (request.getStartDate() != null && request.getEndDate() != null) {
            totalVacationDays = calculateActualVacationDays(request.getStartDate(), request.getEndDate()); // counting only working days
        }

        return dailySalary * totalVacationDays;
    }

    /**
     * @param startDate start vacation
     * @param endDate end vacation
     * @return working days for which need to pay
     */
    private int calculateActualVacationDays(LocalDate startDate, LocalDate endDate) {
        int workedDays = 0;
        for (LocalDate date : startDate.datesUntil(endDate.plusDays(1)).collect(Collectors.toList())) { // make list dates between start and end vacation
            if (!dayOffProxy.isDayOff(date)) { // don't count weekends or holidays
                workedDays++;
            }
        }

        return workedDays;
    }
}

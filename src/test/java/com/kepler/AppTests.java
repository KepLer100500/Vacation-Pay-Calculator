package com.kepler;

import com.kepler.model.VacationRequest;
import com.kepler.service.VacationCalculatorService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class AppTests {
    @Autowired
    private VacationCalculatorService vacationCalculatorService;

    /**
     * case: user define only salary and count days
     */
    @Test
    @DisplayName("partial test case")
    public void testCalculateVacationPayWithoutDates() {
        VacationRequest request = VacationRequest
                .builder()
                .averageSalary(60000)
                .vacationDays(7)
                .build();
        double vacationPay = vacationCalculatorService.calculateVacationPay(request);
        assertEquals(14334.47, vacationPay, 0.1);
    }

    /**
     * case: user define salary, count days, start vacation, end vacation
     */
    @Test
    @DisplayName("full test case")
    public void testCalculateVacationPayWithHolidays() {
        VacationRequest request = VacationRequest
                .builder()
                .averageSalary(60000)
                .vacationDays(7)
                .startDate(LocalDate.of(2024, 3, 4))
                .endDate(LocalDate.of(2024, 3, 10))
                .build();
        double vacationPay = vacationCalculatorService.calculateVacationPay(request);
        assertEquals(8191.12, vacationPay, 0.1);
    }

}

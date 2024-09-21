package com.kepler;

import com.kepler.model.VacationRequest;
import com.kepler.service.VacationCalculatorService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import java.time.LocalDate;


import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class AppTests {
    @Autowired
    private VacationCalculatorService vacationCalculatorService;
    @Autowired
    private RestTemplate restTemplate;
    @Value("${point.url}")
    private String dayOffUrl;

    @Test
    @Order(10)
    @DisplayName("availability external api")
    public void testIsDayOffApi() {
        String url = "https://isdayoff.ru/api/getdata?year=2024&month=3&day=8";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        assertEquals(response.getBody(), "1");
    }

    @Test
    @Order(20)
    @DisplayName("partial request")
    public void testCalculateVacationPayWithoutDates() {
        VacationRequest request = VacationRequest
                .builder()
                .averageSalary(60000)
                .vacationDays(7)
                .build();
        double vacationPay = vacationCalculatorService.calculateVacationPay(request);
        assertEquals(14334.47, vacationPay, 0.1);
    }

    @Test
    @Order(30)
    @DisplayName("full request")
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

    @Test
    @Order(40)
    @DisplayName("negative salary")
    void testNegativeAverageSalary() {
        VacationRequest request = VacationRequest
                .builder()
                .averageSalary(-100000)
                .vacationDays(20)
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(10))
                .build();

        assertNull(vacationCalculatorService.calculateVacationPay(request));
    }

    @Test
    @Order(50)
    @DisplayName("zero salary")
    void testZeroAverageSalary() {
        VacationRequest request = VacationRequest
                .builder()
                .averageSalary(0)
                .vacationDays(20)
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(10))
                .build();

        assertNull(vacationCalculatorService.calculateVacationPay(request));
    }

    @Test
    @Order(60)
    @DisplayName("negative days")
    void testNegativeDays() {
        VacationRequest request = VacationRequest
                .builder()
                .averageSalary(100000)
                .vacationDays(-20)
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(10))
                .build();

        assertNull(vacationCalculatorService.calculateVacationPay(request));
    }

    @Test
    @Order(70)
    @DisplayName("zero days")
    void testZeroDays() {
        VacationRequest request = VacationRequest
                .builder()
                .averageSalary(100000)
                .vacationDays(0)
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(10))
                .build();

        assertNull(vacationCalculatorService.calculateVacationPay(request));
    }

    @Test
    @Order(80)
    @DisplayName("start day is after end day")
    void testStartDateAfterEndDate() {
        VacationRequest request = VacationRequest
                .builder()
                .averageSalary(100000)
                .vacationDays(0)
                .startDate(LocalDate.now().plusDays(10))
                .endDate(LocalDate.now())
                .build();

        assertNull(vacationCalculatorService.calculateVacationPay(request));
    }

}

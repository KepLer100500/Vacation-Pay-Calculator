package com.kepler.controller;

import com.kepler.model.VacationRequest;
import com.kepler.model.VacationResponse;
import com.kepler.service.VacationCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@RestController
@RequestMapping("/api/v1")
public class VacationCalculatorController {
    @Autowired
    private VacationCalculatorService vacationCalculatorService;

    @GetMapping("/calculate")
    public ResponseEntity<VacationResponse> calculateVacationPay(@RequestParam double averageSalary,
                                                                 @RequestParam int vacationDays,
                                                                 @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                                 @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        VacationRequest request = VacationRequest
                .builder()
                .averageSalary(averageSalary)
                .vacationDays(vacationDays)
                .startDate(startDate)
                .endDate(endDate)
                .build();

        VacationResponse response = VacationResponse
                .builder()
                .totalPay(
                        vacationCalculatorService.calculateVacationPay(request)
                )
                .build();

        return ResponseEntity.ok(response);
    }
}

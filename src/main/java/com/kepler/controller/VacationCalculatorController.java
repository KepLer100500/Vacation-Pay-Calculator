package com.kepler.controller;

import com.kepler.model.VacationRequest;
import com.kepler.model.VacationResponse;
import com.kepler.service.VacationCalculatorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@RestController
@RequestMapping("/api/v1")
@Slf4j
public class VacationCalculatorController {
    @Autowired
    private VacationCalculatorService vacationCalculatorService;

    @GetMapping("/calculate")
    public ResponseEntity<?> calculateVacationPay(@RequestParam double averageSalary,
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

        Double totalPay = vacationCalculatorService.calculateVacationPay(request);

        VacationResponse response = VacationResponse
                .builder()
                .totalPay(totalPay)
                .build();

        HttpStatus httpStatus;
        if (totalPay != null) { // service returns null if wrong request
            httpStatus = HttpStatus.OK;
        } else {
            httpStatus = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(response, httpStatus);

    }
}

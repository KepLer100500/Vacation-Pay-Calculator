package com.kepler.controller;

import com.kepler.model.VacationResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;


@RestController
@RequestMapping("/api/v1")
public class VacationCalculatorController {
    @GetMapping("/calculate")
    public ResponseEntity<VacationResponse> calculateVacationPay(@RequestParam double averageSalary,
                                                                 @RequestParam int vacationDays,
                                                                 @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                                 @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        return ResponseEntity.ok(VacationResponse.builder().totalPay(1234567).build());
    }
}

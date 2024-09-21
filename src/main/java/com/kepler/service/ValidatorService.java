package com.kepler.service;

import com.kepler.model.VacationRequest;
import org.springframework.stereotype.Service;

@Service
public class ValidatorService {
    public boolean isRequestCorrect(VacationRequest request) {
        if (request.getAverageSalary() < 0) {
            return false;
        }

        if (request.getAverageSalary() == 0) {
            return false;
        }

        if (request.getVacationDays() < 0) {
            return false;
        }

        if (request.getVacationDays() == 0) {
            return false;
        }

        if (request.getStartDate() != null
                && request.getEndDate() != null
                && request.getStartDate().isAfter(request.getEndDate())) {
            return false;
        }

        if(request.getStartDate() != null && request.getEndDate() == null) {
            return false;
        }

        if(request.getEndDate() != null && request.getStartDate() == null) {
            return false;
        }

        return true;
    }
}

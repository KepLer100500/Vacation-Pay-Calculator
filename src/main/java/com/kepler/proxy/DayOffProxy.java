package com.kepler.proxy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Objects;

@Component
@Slf4j
public class DayOffProxy {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${point.url}")
    private String dayOffUrl;

    /**
     * @param date checking date
     * @return true is day off, or false
     */
    public Boolean isDayOff(LocalDate date) {
        /*
        Api isdayoff.ru returns sign - is day off or not
        08.03.2024 -> https://isdayoff.ru/api/getdata?year=2024&month=3&day=8 -> 1
        05.03.2024 -> https://isdayoff.ru/api/getdata?year=2024&month=3&day=5 -> 0
         */

        String url = String.format(dayOffUrl + "/getdata?year=%d&month=%d&day=%d", date.getYear(), date.getMonthValue(), date.getDayOfMonth()); // build request for external api
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return Objects.equals(response.getBody(), "1"); // return true is day off, false is working day
        } else {
            throw new RuntimeException("Bad data: " + response.getStatusCode());
        }
    }
}

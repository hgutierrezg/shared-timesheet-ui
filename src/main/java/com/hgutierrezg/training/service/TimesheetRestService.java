package com.hgutierrezg.training.service;

import com.hgutierrezg.training.dto.TimesheetDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@AllArgsConstructor
public class TimesheetRestService {

    private final RestTemplate restTemplate;
    private static final String SERVICE_URL = "http://localhost:8081/timesheet";

    public List<TimesheetDto> getTimesheets() {
        return restTemplate.getForObject(SERVICE_URL, List.class);
    }

    public Long createTimesheet(TimesheetDto timesheetDto) {
        return restTemplate.postForEntity(SERVICE_URL, new HttpEntity<>(timesheetDto), Long.class).getBody();
    }

    public void updateTimesheet(TimesheetDto timesheetDto) {
        restTemplate.put(SERVICE_URL, new HttpEntity<>(timesheetDto));
    }

    public void deleteTimesheet(Long id) {
        restTemplate.delete(SERVICE_URL + "/" + id);
    }
}

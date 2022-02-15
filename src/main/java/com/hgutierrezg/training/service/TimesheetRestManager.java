package com.hgutierrezg.training.service;

import com.hgutierrezg.training.dto.TimesheetDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@PropertySource(value = {"classpath:application.properties"})
public class TimesheetRestManager {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${shared.timesheet.service.url}")
    private String serviceUrl;

    public List<TimesheetDto> getTimesheets() {
        return restTemplate.getForObject(serviceUrl, List.class);
    }

    public Long createTimesheet(TimesheetDto timesheetDto) {
        return restTemplate.postForEntity(serviceUrl, new HttpEntity(timesheetDto), Long.class).getBody();
    }

    public void updateTimesheet(TimesheetDto timesheetDto) {
        restTemplate.put(serviceUrl, new HttpEntity(timesheetDto));
    }

    public void deleteTimesheet(Long id) {
        restTemplate.delete(serviceUrl + "/" + id);
    }
}

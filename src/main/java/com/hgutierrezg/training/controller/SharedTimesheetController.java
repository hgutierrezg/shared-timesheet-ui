package com.hgutierrezg.training.controller;

import com.hgutierrezg.training.dto.TimesheetDto;
import com.hgutierrezg.training.service.TimesheetRestService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/times")
@AllArgsConstructor
public class SharedTimesheetController {

    private final TimesheetRestService timesheetRestService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TimesheetDto>> getAllTimesheets() {
        return ResponseEntity.ok(timesheetRestService.getTimesheets());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> createTimesheet(@RequestBody TimesheetDto timesheetDto) {
        Long id = timesheetRestService.createTimesheet(timesheetDto);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateTimesheet(@RequestBody TimesheetDto timesheetDto) {
        timesheetRestService.updateTimesheet(timesheetDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteTimesheet(@PathVariable Long id) {
        timesheetRestService.deleteTimesheet(id);
        return ResponseEntity.ok().build();
    }
}
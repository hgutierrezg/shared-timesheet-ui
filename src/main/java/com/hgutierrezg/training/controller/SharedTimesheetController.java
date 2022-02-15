package com.hgutierrezg.training.controller;

import com.hgutierrezg.training.dto.TimesheetDto;
import com.hgutierrezg.training.service.TimesheetRestManager;
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

    private final TimesheetRestManager timesheetRestManager;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TimesheetDto>> getAllTimesheets() {
        return ResponseEntity.ok(timesheetRestManager.getTimesheets());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> createTimesheet(@RequestBody TimesheetDto timesheetDto) {
        Long id = timesheetRestManager.createTimesheet(timesheetDto);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateTimesheet(@RequestBody TimesheetDto timesheetDto) {
        timesheetRestManager.updateTimesheet(timesheetDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteTimesheet(@PathVariable Long id) {
        timesheetRestManager.deleteTimesheet(id);
        return ResponseEntity.ok().build();
    }
}
package com.example.scheduleservice.api.controller;


import com.example.scheduleservice.api.mapper.ScheduleMapper;
import com.example.scheduleservice.api.model.request.ScheduleRequest;
import com.example.scheduleservice.api.model.response.ScheduleResponse;
import com.example.scheduleservice.domain.entity.Schedule;
import com.example.scheduleservice.domain.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService service;

    @Autowired
    private ScheduleMapper mapper;


    @GetMapping
    public ResponseEntity<List<ScheduleResponse>> getAll() {
        List<Schedule> schedules = service.findAll();
        List<ScheduleResponse> scheduleResponses = mapper.toListScheduleResponse(schedules);
        return ResponseEntity.status(HttpStatus.OK).body(scheduleResponses);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponse> getById(@PathVariable Long id) {
        Optional<Schedule> optionalSchedule = service.findById(id);
        if (optionalSchedule.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        ScheduleResponse scheduleResponse = mapper.toScheduleResponse(optionalSchedule.get());
        return ResponseEntity.status(HttpStatus.OK).body(scheduleResponse);
    }

    @PostMapping
    public ResponseEntity<ScheduleResponse> save(@Valid @RequestBody ScheduleRequest scheduleRequest) {
        Schedule schedule = mapper.toSchedule(scheduleRequest);
        Schedule scheduleSave = service.save(schedule);
        ScheduleResponse scheduleResponse = mapper.toScheduleResponse(scheduleSave);
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleResponse);
    }

}

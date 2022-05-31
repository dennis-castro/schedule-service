package com.example.scheduleservice.api.controller;


import com.example.scheduleservice.api.model.request.ScheduleRequest;
import com.example.scheduleservice.api.model.response.ScheduleResponse;
import com.example.scheduleservice.domain.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/squedule")
public class ScheduleController {

    @Autowired
    private ScheduleService service;


    @GetMapping
    public ResponseEntity<List<ScheduleResponse>> getAll(){

    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponse> getById(@PathVariable Long id){

    }

    @PostMapping
    public ResponseEntity<ScheduleResponse>  save(@RequestBody ScheduleRequest scheduleRequest){

    }

}

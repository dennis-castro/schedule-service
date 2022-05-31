package com.example.scheduleservice.api.controller;

import com.example.scheduleservice.api.mapper.PatientMapper;
import com.example.scheduleservice.api.model.request.PatientRequest;
import com.example.scheduleservice.api.model.response.PatientResponse;
import com.example.scheduleservice.domain.entity.Patient;
import com.example.scheduleservice.domain.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequestMapping("/patient")
@RestController
public class PatientController {

    @Autowired
    private PatientService service;

    @Autowired
    private PatientMapper mapper;

    @PostMapping
    public ResponseEntity<PatientResponse> save(@Valid @RequestBody PatientRequest patientRequest) {
        Patient patient = mapper.toPatient(patientRequest);
        Patient patientSave = service.save(patient);
        PatientResponse patientResponse = mapper.toPatientResponse(patientSave);
        return ResponseEntity.status(HttpStatus.CREATED).body(patientResponse);
    }


    @GetMapping
    public ResponseEntity<List<PatientResponse>> getAll() {
        List<Patient> patients = service.findAll();
        List<PatientResponse> patientResponses = mapper.toListPatientResponse(patients);
        return ResponseEntity.status(HttpStatus.OK).body(patientResponses);
    }


    @GetMapping("/{id}")
    public ResponseEntity<PatientResponse> getById(@PathVariable Long id) {
        Optional<Patient> patientOptional = service.findById(id);
        if (patientOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toPatientResponse(patientOptional.get()));
    }


    @PutMapping("/{id}")
    public ResponseEntity<PatientResponse> update(@Valid @PathVariable Long id, @RequestBody PatientRequest patientRequest) {
        Patient patient = mapper.toPatient(patientRequest);
        Patient patientSave = service.update(id, patient);
        PatientResponse patientResponse = mapper.toPatientResponse(patientSave);
        return ResponseEntity.status(HttpStatus.OK).body(patientResponse);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}

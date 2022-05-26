package com.example.scheduleservice.api.controller;

import com.example.scheduleservice.domain.entity.Patient;
import com.example.scheduleservice.domain.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/patient")
@RestController
public class PatientController {

    @Autowired
    private PatientService service;

    @PostMapping
    public ResponseEntity<Patient> create(@RequestBody Patient request){
        Patient patient = service.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(patient);
    }

    @GetMapping
    public ResponseEntity<List<Patient>> getAll(){
        List<Patient> patients = service.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(patients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getById(@PathVariable Long id){
        Optional<Patient> optPatient = service.findById(id);
        if (optPatient.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(optPatient.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<Patient>> update(@PathVariable Long id,
                                                    @RequestBody Patient request){
        Optional<Patient> updatePatient = service.update(id,request);
        if (updatePatient.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(updatePatient);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id){
        Optional<Patient> optPatient = service.findById(id);
        if (optPatient.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body("the patient was deleted");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}

package com.example.scheduleservice.domain.service;

import com.example.scheduleservice.domain.entity.Patient;
import com.example.scheduleservice.domain.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository repository;

    public Patient save(Patient patient){
        return repository.save(patient);
    }

    public Optional<Patient> getById(Long id){
        return repository.findById(id);
    }

    public List<Patient> getAll(){
        return repository.findAll();
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

}

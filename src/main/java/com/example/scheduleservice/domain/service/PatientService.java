package com.example.scheduleservice.domain.service;

import com.example.scheduleservice.domain.entity.Patient;
import com.example.scheduleservice.domain.repository.PatientRepository;
import com.example.scheduleservice.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PatientService {

    @Autowired
    private PatientRepository repository;


    public Patient save(Patient patient) {
        boolean existDocument = false;

        Optional<Patient> patientByDocument = repository.findByDocument(patient.getDocument());
        if (patientByDocument.isPresent()) {
            if (!patientByDocument.get().getId().equals(patient.getId())) {
                existDocument = true;
            }
        }
        if (existDocument) {
            throw new BusinessException("Document already registered.");
        }

        return repository.save(patient);
    }


    public List<Patient> findAll() {
        return repository.findAll();
    }


    public Patient update(Long id, Patient patient) {
        Optional<Patient> optionalPatient = repository.findById(id);
        if (optionalPatient.isEmpty()) {
            throw new BusinessException("Patient is not registered.");
        }

        patient.setId(id);

        return repository.save(patient);
    }


    public Optional<Patient> findById(Long id) {
        return repository.findById(id);
    }


    public void delete(Long id) {
        repository.deleteById(id);
    }


}

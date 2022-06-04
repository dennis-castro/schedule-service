package com.example.scheduleservice.domain.service;

import com.example.scheduleservice.domain.entity.Patient;
import com.example.scheduleservice.domain.repository.PatientRepository;
import com.example.scheduleservice.exception.BusinessException;
import org.springframework.beans.BeanUtils;
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
        Optional<Patient> patientByDocument = repository.findByDocument(patient.getDocument());
        if (patientByDocument.isPresent()) {
            throw new BusinessException("Patient already registered!");
        }
        return repository.save(patient);
    }


    public List<Patient> findAll() {
        return repository.findAll();
    }


    public Patient update(Long id, Patient patient) {
        boolean document = false;

        Optional<Patient> patientOptional = repository.findById(id);
        if (patientOptional.isEmpty()) {
            throw new BusinessException("Patient is not found");
        }
        if (!patientOptional.get().getDocument().equals(patient.getDocument())) {
            document = true;
        }
        if (document) {
            throw new BusinessException("the document cannot be changed");
        }

        Patient patientUpdate = patientOptional.get();
        BeanUtils.copyProperties(patient, patientUpdate,"id");
        return repository.save(patientUpdate);
    }


    public Optional<Patient> findById(Long id) {
        return repository.findById(id);
    }


    public void delete(Long id) {
        repository.deleteById(id);
    }


}

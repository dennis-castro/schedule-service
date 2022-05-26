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


    public Patient save(Patient patient){
        Optional<Patient> optPatient = repository.findByDocument(patient.getDocument());
        if (optPatient.isPresent()){
            throw  new BusinessException("The patient is already registered");
        }
        return repository.save(patient);
    }

    public Optional<Patient> findById(Long id){
        return repository.findById(id);
    }

    public List<Patient> findAll(){
        return repository.findAll();
    }

    public Optional<Patient> update(Long id, Patient request){
        Optional<Patient> newPatient = findById(id);
        if(newPatient.isPresent()){
            BeanUtils.copyProperties(request, newPatient.get(),"id");
            return newPatient;
        }
        return Optional.empty();
    }


    public void delete(Long id){
        repository.deleteById(id);
    }

}

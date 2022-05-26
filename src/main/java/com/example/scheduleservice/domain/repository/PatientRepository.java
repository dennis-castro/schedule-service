package com.example.scheduleservice.domain.repository;

import com.example.scheduleservice.domain.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient,Long> {


    Optional<Patient> findByDocument (String document);



}

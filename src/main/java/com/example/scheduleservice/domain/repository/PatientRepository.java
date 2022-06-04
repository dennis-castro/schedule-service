package com.example.scheduleservice.domain.repository;

import com.example.scheduleservice.domain.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {


    Optional<Patient> findByDocument(String document);


}

package com.example.scheduleservice.domain.repository;

import com.example.scheduleservice.domain.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,Long> {

}

package com.example.scheduleservice.domain.service;


import com.example.scheduleservice.domain.entity.Patient;
import com.example.scheduleservice.domain.entity.Schedule;
import com.example.scheduleservice.domain.repository.ScheduleRepository;
import com.example.scheduleservice.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ScheduleService {

    @Autowired
    private ScheduleRepository repository;

    @Autowired
    private PatientService patientService;


    public List<Schedule> findAll() {
        return repository.findAll();
    }

    public Optional<Schedule> findById(Long id) {
        return repository.findById(id);
    }

    public Schedule save(Schedule schedule) {
        Optional<Patient> patientOptional = patientService.findById(schedule.getPatient().getId());

        if (patientOptional.isEmpty()) {
            throw new BusinessException("Patient is not found");
        }

        Optional<Schedule> optionalDateTime = repository.findByDateTime(schedule.getDateTime());

        if (optionalDateTime.isPresent()) {
            throw new BusinessException("There is already an appointment for this time.");
        }

        schedule.setPatient(patientOptional.get());
        schedule.setCreationDate(LocalDateTime.now());

        return repository.save(schedule);
    }


}

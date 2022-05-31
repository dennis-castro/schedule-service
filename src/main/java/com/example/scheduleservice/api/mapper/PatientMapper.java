package com.example.scheduleservice.api.mapper;


import com.example.scheduleservice.api.model.request.PatientRequest;
import com.example.scheduleservice.api.model.response.PatientResponse;
import com.example.scheduleservice.domain.entity.Patient;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PatientMapper {

    @Autowired
    private  ModelMapper mapper;

    public Patient toPatient(PatientRequest request){
        return mapper.map(request,Patient.class);
    }

    public PatientResponse toPatientResponse(Patient patient){
        return mapper.map(patient,PatientResponse.class);
    }

    public List<PatientResponse> toListPatientResponse(List<Patient> patients){
        return patients.stream()
                .map(patient -> mapper.map(patient,PatientResponse.class))
                .collect(Collectors.toList());
    }

}

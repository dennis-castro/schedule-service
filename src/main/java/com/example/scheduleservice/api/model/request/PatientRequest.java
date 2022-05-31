package com.example.scheduleservice.api.model.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientRequest {

    @NotBlank(message = "The patient's name is mandatory")
    private String name;

    @NotBlank(message = "The patient's last name is mandatory")
    private String lastName;

    @NotBlank(message = "The patient's email name is mandatory")
    @Email
    private String email;

    @NotBlank(message = "The patient's document is mandatory")
    private String document;
}

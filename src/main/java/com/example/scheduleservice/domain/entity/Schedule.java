package com.example.scheduleservice.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@Entity
@Table(name = "schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "date_hour")
    private LocalDateTime dateTime;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "patient_fk")
    private Patient patient;


}

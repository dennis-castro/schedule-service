package com.example.scheduleservice.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

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

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinColumn(name = "id_patient")
    private Patient patient;


}

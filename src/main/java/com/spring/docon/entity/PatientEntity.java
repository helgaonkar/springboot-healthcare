package com.spring.docon.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "patient")
public class PatientEntity {

    @Id
    @GeneratedValue
    @Column(name = "patient_id")
    private Long patientId;

    @Column(name = "blood_group")
    private String bloodGroup;

    @Column(name = "height")
    private Integer height;

    @Column(name = "weight")
    private Integer weight;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "person_id")
    private PersonEntity personEntity;
}





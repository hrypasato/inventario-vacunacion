package com.example.inventariovacunas.models;

import lombok.Data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "vacunas")
public class Vacuna implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="vacuna_id")
    private Long vacunaId;

    @Column(name="vacuna_nombre")
    private String vacunaNombre;
}
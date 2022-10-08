package com.example.inventariovacunas.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Entity
@Table(name = "vacunaciones")
public class Vacunacion implements Serializable {
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="vacunacion_id")
    @Schema(readOnly = true)
    private Long vacunacionId;

    @Column(name="fecha_vacunacion")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fechaVacunacion;

    @Column(name="numero_dosis")
    private Integer numeroDosis;

    @ManyToOne(optional = false)
    @JoinColumn(name = "empleado_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Empleado empleado;

    @ManyToOne(optional = false)
    @JoinColumn(name = "vacuna_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Schema(readOnly = true)
    private Vacuna vacuna;

    @Transient
    @Schema(writeOnly = true)
    private Long empleadoId;
    
    @Transient
    @Schema(writeOnly = true)
    private Long vacunaId;
}

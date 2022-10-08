package com.example.inventariovacunas.models;


import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "empleados")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Empleado implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="empleado_id")
    @Schema(readOnly = true)
    private Long empleadoId;

    @NotBlank(message = "Identificacion es obligatoria")
    @Column(unique = true)
    @Pattern(regexp = "^[\\d+]{10}")
    @Schema(example = "0101020304")
    private String identificacion;
    
    @NotBlank(message = "Los nombres son obligatorios")
    @Pattern(regexp = "^[a-zA-Z]+\\s[a-zA-Z]+$")
    @Schema(example = "Juan José")
    private String nombres;
    
    @NotBlank(message = "Los apellidos son obligatorios")
    @Pattern(regexp = "^[a-zA-Z]+\\s[a-zA-Z]+$")
    @Schema(example = "Perez Durán")
    private String apellidos;
    
    @NotBlank(message = "Los nombres son obligatorios")
    @Email
    @Schema(example = "juanperezd@example.com")
    private String correo;

    @OneToOne(mappedBy = "empleado", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    @JsonIgnore
    private DatoEmpleado datoEmpleado;
}

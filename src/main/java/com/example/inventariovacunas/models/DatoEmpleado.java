package com.example.inventariovacunas.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Entity
@Table(name = "datos_empleados")
public class DatoEmpleado implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="dato_empleado_id")
    @Schema(readOnly = true)
    private Long datoEmpleadoId;

    @Column(name="fecha_nacimiento")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(example = "2022-10-24")
    private Date fechaNacimiento;
    private String direccion;

    @Column(name="telefono_movil")
    @NotBlank(message = "El numero de teléfono es obligatorio")
    @Schema(example = "0998765432")
    private String telefonoMovil;

    @Column(name="esta_vacunado")
    @Schema(example = "true")
    private Boolean estaVacunado;

    @OneToOne
    @JoinColumn(name = "empleado_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Schema(hidden = true)
    @JsonIgnore
    private Empleado empleado;

    @Transient
    @Schema(writeOnly = true, example = "2")
    private Long empleadoId;
}

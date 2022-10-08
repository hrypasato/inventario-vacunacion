package com.example.inventariovacunas.schemas;

import java.util.List;

import com.example.inventariovacunas.models.DatoEmpleado;
import com.example.inventariovacunas.models.Vacunacion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DatoEmpleadoVacunacion {
    private DatoEmpleado datoEmpleado;
    private List<Vacunacion> vacunaciones;
}

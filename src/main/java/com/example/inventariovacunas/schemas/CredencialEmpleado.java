package com.example.inventariovacunas.schemas;

import com.example.inventariovacunas.models.Credencial;
import com.example.inventariovacunas.models.Empleado;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CredencialEmpleado {
    private Empleado empleado;
    private Credencial credencial;
}

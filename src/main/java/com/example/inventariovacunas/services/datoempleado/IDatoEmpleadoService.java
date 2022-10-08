package com.example.inventariovacunas.services.datoempleado;

import java.util.List;

import com.example.inventariovacunas.models.DatoEmpleado;
import com.example.inventariovacunas.models.Empleado;


public interface IDatoEmpleadoService {
    DatoEmpleado findDatoEmpleadoByEmpleado(Empleado empleado);
    DatoEmpleado findDatoEmpleadoById(Long id);
    List<DatoEmpleado> findByEstaVacunado(Boolean estaVacunado); 
    DatoEmpleado createDatoEmpleado(DatoEmpleado datoEmpleado);
    DatoEmpleado updateDatoEmpleado(Long id, DatoEmpleado datoEmpleado);
    DatoEmpleado deleteDatoEmpleado(Long id);
}

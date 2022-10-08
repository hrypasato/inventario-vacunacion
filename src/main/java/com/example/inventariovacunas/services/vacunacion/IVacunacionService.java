package com.example.inventariovacunas.services.vacunacion;

import java.util.Date;
import java.util.List;

import com.example.inventariovacunas.models.Empleado;
import com.example.inventariovacunas.models.Vacunacion;

public interface IVacunacionService {
    Vacunacion createVacunacion(Vacunacion vacunacion);
    List<Vacunacion> findVacunacionesInRange(Date from, Date to);
    List<Vacunacion> findVacunacionesByType(Long vacunaId);
    List<Vacunacion> findVacunacionesByType(String vacunaNombre);
    List<Vacunacion> findVacunacionesByEmpleado(Empleado empleado);
}

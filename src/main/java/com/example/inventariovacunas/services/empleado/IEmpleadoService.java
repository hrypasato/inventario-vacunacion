package com.example.inventariovacunas.services.empleado;

import java.util.List;

import com.example.inventariovacunas.models.Empleado;

public interface IEmpleadoService {
    public List<Empleado> findAllEmpleados();
    public Empleado findEmpleadoById(Long id);
    public Empleado createEmpleado(Empleado empleado);
    public Empleado updateEmpleado(Empleado empleado);
    public Empleado deleteEmpleado(Long id);
}

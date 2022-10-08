package com.example.inventariovacunas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.inventariovacunas.models.DatoEmpleado;
import com.example.inventariovacunas.models.Empleado;

public interface DatoEmpleadoRepository extends JpaRepository<DatoEmpleado, Long> {
    Optional<DatoEmpleado> findByEmpleado(Empleado empleado);
    Optional<DatoEmpleado> findByDatoEmpleadoId(Long datoEmpleadoId);
    List<DatoEmpleado> findByEstaVacunado(Boolean estaVacunado);
}

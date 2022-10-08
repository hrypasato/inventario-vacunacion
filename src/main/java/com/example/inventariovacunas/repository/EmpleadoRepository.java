package com.example.inventariovacunas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.inventariovacunas.models.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long>{
    Optional<Empleado> findByEmpleadoId(Long empleadoId);
}

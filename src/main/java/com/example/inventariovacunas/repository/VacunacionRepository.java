package com.example.inventariovacunas.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.inventariovacunas.models.Empleado;
import com.example.inventariovacunas.models.Vacuna;
import com.example.inventariovacunas.models.Vacunacion;

public interface VacunacionRepository extends JpaRepository<Vacunacion, Long>{
    List<Vacunacion> findByFechaVacunacionBetween(Date from, Date to);
    List<Vacunacion> findByVacuna(Vacuna vacuna);
    List<Vacunacion> findByEmpleado(Empleado empleado);
}

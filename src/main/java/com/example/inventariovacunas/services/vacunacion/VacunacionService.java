package com.example.inventariovacunas.services.vacunacion;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.inventariovacunas.models.Empleado;
import com.example.inventariovacunas.models.Vacuna;
import com.example.inventariovacunas.models.Vacunacion;
import com.example.inventariovacunas.repository.VacunacionRepository;
import com.example.inventariovacunas.services.empleado.IEmpleadoService;
import com.example.inventariovacunas.services.vacuna.IVacunaService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VacunacionService implements IVacunacionService{

    private final VacunacionRepository vacunacionRepository;

    @Autowired
    private IEmpleadoService empleadoService;

    @Autowired
    private IVacunaService vacunaService;

    @Override
    public Vacunacion createVacunacion(Vacunacion vacunacion) {
        Long empleadoId = vacunacion.getEmpleadoId();
        Long vacunaId = vacunacion.getVacunaId();

        Empleado empleado = empleadoService.findEmpleadoById(empleadoId);
        Vacuna vacuna = vacunaService.findVacuna(vacunaId);
        
        vacunacion.setEmpleado(empleado);
        vacunacion.setVacuna(vacuna);

        return vacunacionRepository.save(vacunacion);
    }

    @Override
    public List<Vacunacion> findVacunacionesInRange(Date from, Date to) {
        return vacunacionRepository.findByFechaVacunacionBetween(from, to);
    }

    @Override
    public List<Vacunacion> findVacunacionesByType(Long vacunaId) {
        Vacuna currentVacuna = vacunaService.findVacuna(vacunaId);
        return vacunacionRepository.findByVacuna(currentVacuna);
    }

    @Override
    public List<Vacunacion> findVacunacionesByType(String vacunaNombre) {
        Vacuna currentVacuna = vacunaService.findVacuna(vacunaNombre);
        return vacunacionRepository.findByVacuna(currentVacuna);
    }

    @Override
    public List<Vacunacion> findVacunacionesByEmpleado(Empleado empleado) {
        return vacunacionRepository.findByEmpleado(empleado);
    }
    
}

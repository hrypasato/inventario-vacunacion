package com.example.inventariovacunas.services.datoempleado;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.inventariovacunas.models.DatoEmpleado;
import com.example.inventariovacunas.models.Empleado;
import com.example.inventariovacunas.repository.DatoEmpleadoRepository;
import com.example.inventariovacunas.services.empleado.IEmpleadoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DatoEmpleadoService implements IDatoEmpleadoService{
    
    private final DatoEmpleadoRepository datoEmpleadoRepository;

    @Autowired
    private IEmpleadoService empleadoService;


    @Override
    public DatoEmpleado findDatoEmpleadoByEmpleado(Empleado empleado) {
        Optional<DatoEmpleado> datoEmpleado = datoEmpleadoRepository.findByEmpleado(empleado);
        return datoEmpleado.isEmpty() ? null : datoEmpleado.get();
    }

    @Override
    public DatoEmpleado findDatoEmpleadoById(Long id) {
        Optional<DatoEmpleado> datoEmpleado = datoEmpleadoRepository.findByDatoEmpleadoId(id); 
        return datoEmpleado.isEmpty() ? null : datoEmpleado.get();
    }

    @Override
    public DatoEmpleado createDatoEmpleado(DatoEmpleado datoEmpleado) {
        datoEmpleado = setEmpleado(datoEmpleado);
        return datoEmpleadoRepository.save(datoEmpleado);
    }

    @Override
    public DatoEmpleado updateDatoEmpleado(Long id, DatoEmpleado datoEmpleado) {
        DatoEmpleado datoEmpleadoToUpdate = findDatoEmpleadoById(id);
        if(datoEmpleadoToUpdate != null){
            datoEmpleado = setEmpleado(datoEmpleado);
            datoEmpleado.setDatoEmpleadoId(datoEmpleadoToUpdate.getDatoEmpleadoId());
            return datoEmpleadoRepository.save(datoEmpleado);
        }
        return null;
    }

    @Override
    public DatoEmpleado deleteDatoEmpleado(Long id) {
        DatoEmpleado datoEmpleadoToDelete = findDatoEmpleadoById(id);
        if(datoEmpleadoToDelete != null){
            datoEmpleadoRepository.delete(datoEmpleadoToDelete);
            return datoEmpleadoToDelete;
        }
        return null;
    }
    
    private DatoEmpleado setEmpleado(DatoEmpleado datoEmpleado){
        Long empleadoId = datoEmpleado.getEmpleadoId();
        Empleado empleado = empleadoService.findEmpleadoById(empleadoId);
        datoEmpleado.setEmpleado(empleado);
        return datoEmpleado;
    }

    @Override
    public List<DatoEmpleado> findByEstaVacunado(Boolean estaVacunado) {
        return datoEmpleadoRepository.findByEstaVacunado(estaVacunado);
    }

}

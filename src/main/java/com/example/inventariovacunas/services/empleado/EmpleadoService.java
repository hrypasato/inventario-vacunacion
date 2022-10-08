package com.example.inventariovacunas.services.empleado;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.inventariovacunas.models.Empleado;
import com.example.inventariovacunas.repository.EmpleadoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmpleadoService implements IEmpleadoService{

    private final EmpleadoRepository empleadoRepository;

    @Override
    public List<Empleado> findAllEmpleados() {
        return empleadoRepository.findAll();
    }

    @Override
    public Empleado findEmpleadoById(Long id) {
        Optional<Empleado> empleado = empleadoRepository.findByEmpleadoId(id);
        return empleado.isEmpty() ? null : empleado.get();
    }

    @Override
    public Empleado createEmpleado(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    @Override
    public Empleado updateEmpleado(Empleado empleado) {    
        return empleadoRepository.save(empleado);
    }

    @Override
    public Empleado deleteEmpleado(Long id) {
        Empleado empleadoToDelete = findEmpleadoById(id);
        if(empleadoToDelete != null){
            empleadoRepository.delete(empleadoToDelete);
            return empleadoToDelete;
        }
        return null;
    }    
}

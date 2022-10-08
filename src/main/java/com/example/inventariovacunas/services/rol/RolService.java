package com.example.inventariovacunas.services.rol;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.inventariovacunas.models.Rol;
import com.example.inventariovacunas.repository.RolRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RolService implements IRolService{

    private final RolRepository rolRepository;
    
    @Override
    public Rol findRolById(Long id) {
        Optional<Rol> rol = rolRepository.findById(id);
        return rol.get();
    }
    
}

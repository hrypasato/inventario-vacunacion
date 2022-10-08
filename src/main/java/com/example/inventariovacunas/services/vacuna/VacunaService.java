package com.example.inventariovacunas.services.vacuna;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.inventariovacunas.models.Vacuna;
import com.example.inventariovacunas.repository.VacunaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VacunaService implements IVacunaService{

    private final VacunaRepository vacunaRepository;

    @Override
    public List<Vacuna> findAllVacunas() {
        return vacunaRepository.findAll();
    }

    @Override
    public Vacuna findVacuna(Long id) {
        Optional<Vacuna> vacuna = vacunaRepository.findByVacunaId(id);
        return vacuna.isEmpty() ? null : vacuna.get();
    }

    @Override
    public Vacuna findVacuna(String nombre) {
        Optional<Vacuna> vacuna = vacunaRepository.findByVacunaNombre(nombre);
        return vacuna.isEmpty() ? null : vacuna.get();
    }
    
}

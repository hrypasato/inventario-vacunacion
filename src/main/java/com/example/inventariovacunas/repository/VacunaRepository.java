package com.example.inventariovacunas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.inventariovacunas.models.Vacuna;

public interface VacunaRepository extends JpaRepository<Vacuna, Long>{
    Optional<Vacuna> findByVacunaId(Long vacunaId);
    Optional<Vacuna> findByVacunaNombre(String vacunaNombre);
}

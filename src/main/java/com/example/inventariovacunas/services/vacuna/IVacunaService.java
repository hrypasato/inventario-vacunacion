package com.example.inventariovacunas.services.vacuna;

import java.util.List;

import com.example.inventariovacunas.models.Vacuna;

public interface IVacunaService {
    List<Vacuna> findAllVacunas();
    Vacuna findVacuna(Long id);
    Vacuna findVacuna(String nombre);
}

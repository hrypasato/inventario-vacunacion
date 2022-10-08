package com.example.inventariovacunas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.inventariovacunas.models.Vacuna;
import com.example.inventariovacunas.services.vacuna.IVacunaService;

@RestController
@RequestMapping("api/v1/vacunas")
@CrossOrigin(origins = "*")
public class VacunasController {
    @Autowired
    private IVacunaService vacunaService;

    @GetMapping
    public ResponseEntity<List<Vacuna>> listarVacunas(){
        List<Vacuna> vacunas = vacunaService.findAllVacunas();
        return ResponseEntity.ok().body(vacunas);
    }
}

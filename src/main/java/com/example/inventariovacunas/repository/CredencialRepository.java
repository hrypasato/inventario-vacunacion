package com.example.inventariovacunas.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.inventariovacunas.models.Credencial;
import com.example.inventariovacunas.models.CredencialId;

public interface CredencialRepository extends JpaRepository<Credencial, CredencialId> {
    
}

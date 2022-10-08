package com.example.inventariovacunas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.inventariovacunas.models.Rol;

public interface RolRepository extends JpaRepository<Rol, Long>{
    Optional<Rol> findByRolId(Long rolId); 
}

package com.example.inventariovacunas.models;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "credenciales")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Credencial implements Serializable{
    private String usuario;
    private String password;
    
    @EmbeddedId
    private CredencialId credencialId;
}

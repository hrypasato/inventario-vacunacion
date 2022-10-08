package com.example.inventariovacunas.shared;

import java.util.Base64;

import com.example.inventariovacunas.models.Empleado;

public class CredencialsGenerator {
    
    /*
     * username = primerNombre + primerApellido + empleadoId
     */
    public static String generateUsername(Empleado empleado) {
        String[] nombres = empleado.getNombres().split(" ");
        String[] apellidos = empleado.getApellidos().split(" ");
        String username = nombres[0].toLowerCase() 
                        + apellidos[0].toLowerCase() 
                        + String.valueOf(empleado.getEmpleadoId());
        return username;
    }

    public static String encodePassword(String inputString) {
        String password = Base64.getEncoder().encodeToString(inputString.getBytes());
        return password;
    }

}

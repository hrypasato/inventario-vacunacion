package com.example.inventariovacunas.services.credencial;

import com.example.inventariovacunas.models.Credencial;
import com.example.inventariovacunas.models.Empleado;

public interface ICredencialService {
    Credencial findCredenciaByUsernameAndPassword(String username, String password);
    Credencial saveCredencial(String usuario, String password, Empleado empleado);
    Credencial generateCredencials(Empleado empleado);
}

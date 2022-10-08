package com.example.inventariovacunas.services.credencial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.inventariovacunas.models.Credencial;
import com.example.inventariovacunas.models.CredencialId;
import com.example.inventariovacunas.models.Empleado;
import com.example.inventariovacunas.models.Rol;
import com.example.inventariovacunas.repository.CredencialRepository;
import com.example.inventariovacunas.services.rol.IRolService;
import com.example.inventariovacunas.shared.CredencialsGenerator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CredencialService implements ICredencialService{

    private final Long ROL_EMPLEADO = 2l;

    private final CredencialRepository credencialRepository;
    
    @Autowired
    private IRolService rolService;

    @Override
    public Credencial findCredenciaByUsernameAndPassword(String username, String password) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Credencial saveCredencial(String usuario, String password, Empleado empleado) {
        Rol rolEmpleado = rolService.findRolById(ROL_EMPLEADO);

        CredencialId credencialId = CredencialId.builder().empleado(empleado).rol(rolEmpleado).build();
        
        String newEncodedPassword = CredencialsGenerator.encodePassword(password);

        Credencial credencial = Credencial.builder()
                                            .credencialId(credencialId)
                                            .usuario(usuario)
                                            .password(newEncodedPassword)
                                            .build();

        Credencial newCredencial = credencialRepository.save(credencial);
        newCredencial.setPassword(password);
        return newCredencial;
    }
    
    @Override
    public Credencial generateCredencials(Empleado empleado) {
        String usuario = CredencialsGenerator.generateUsername(empleado);
        String password = usuario;
        Credencial newCredencial = saveCredencial(usuario, password, empleado);
        return newCredencial;
    }

}

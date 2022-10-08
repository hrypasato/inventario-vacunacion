package com.example.inventariovacunas.shared;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.inventariovacunas.models.Empleado;

@SpringBootTest
public class CredencialsGeneratorTest {
    
    @Test
    public void testGenerateUsername(){
        
        Empleado empleado = Empleado.builder()
                                    .empleadoId(52l)
                                    .nombres("Ana Maria")
                                    .apellidos("Garcia Valdez")
                                    .build();
        String username = CredencialsGenerator.generateUsername(empleado);
        
        String usernameSpected = "anagarcia52";
        Assertions.assertThat(username).isEqualTo(usernameSpected);
    }

    @Test
    public void testGeneratePassword(){
        String plainText = "sup3r.p4ssw0rd";
        String passwordSpected = "c3VwM3IucDRzc3cwcmQ=";
        String password = CredencialsGenerator.encodePassword(plainText);
        Assertions.assertThat(password).isEqualTo(passwordSpected);

        String plainText1 = "passcj";
        String passwordSpected1 = "cGFzc2Nq";
        String password1 = CredencialsGenerator.encodePassword(plainText1);
        Assertions.assertThat(password1).isEqualTo(passwordSpected1);
    }

}

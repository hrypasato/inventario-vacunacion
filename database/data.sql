INSERT INTO roles (rol_nombre) 
    VALUES  ('ADMINISTRADOR'), 
            ('EMPLEADO');

INSERT INTO vacunas ('vacuna_nombre')
    VALUES  ('Sputnik'),
            ('AstraZeneca'),
            ('Pfizer'),
            ('Jhonson&Jhonson');

--Insertando al usuario administrador

INSERT INTO empleados (identificacion, nombres, apellidos, correo)
    VALUES ('0123456789', 'Carlos Marcelo', 'Jara Mora', 'carlosjara@mail.com');

INSERT INTO credenciales( empleado_id, rol_id, usuario, password)
    VALUES  (1, 1, 'admincarlosjara', 'c3VwM3IucDRzc3cwcmQ=')   --credenciales de administrador (sup3r.p4ssw0rd)
            (1, 2, 'carlosjara1', 'cGFzc2Nq');                  --credenciales de usuario (passcj)

--Insertando informacion del empleado
INSERT INTO datos_empleados ( empleado_id, fecha_nacimiento, direccion, telefono_movil, esta_vacunado )
    VALUES ( 1, '1998-02-12', 'Sucre 5-11 y Borrero, Cuenca', '0990120322', true );

--Primera vacuna de Pfizer
INSERT INTO (empleado_id, vacuna_id, fecha_vacunacion, numero_dosis)
    VALUES (1, 3, '2022-07-08', 1 )
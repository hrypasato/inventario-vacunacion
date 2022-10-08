package com.example.inventariovacunas.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.inventariovacunas.errors.Message;
import com.example.inventariovacunas.models.DatoEmpleado;
import com.example.inventariovacunas.models.Empleado;
import com.example.inventariovacunas.models.Vacunacion;
import com.example.inventariovacunas.schemas.DatoEmpleadoVacunacion;
import com.example.inventariovacunas.services.datoempleado.IDatoEmpleadoService;
import com.example.inventariovacunas.services.empleado.IEmpleadoService;
import com.example.inventariovacunas.services.vacunacion.IVacunacionService;

import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("api/v1/datosempleados")
@CrossOrigin(origins = "*")
public class DatoEmpleadoController {

    @Autowired
    private IDatoEmpleadoService datoEmpleadoService;

    @Autowired
    private IVacunacionService vacunacionService;


    @Autowired
    private IEmpleadoService empleadoService;

    @GetMapping(value = "/{empleadoId}")
    public ResponseEntity<DatoEmpleadoVacunacion> obtenerDatosEmpleado(
        @Parameter(example = "1")
        @PathVariable("empleadoId") Long empleadoId) {
        
        Empleado empleado = empleadoService.findEmpleadoById(empleadoId);

        if(empleado != null){

            DatoEmpleado datoEmpleado = datoEmpleadoService.findDatoEmpleadoByEmpleado(empleado);
            List<Vacunacion> vacunas = vacunacionService.findVacunacionesByEmpleado(empleado);
            
            DatoEmpleadoVacunacion datoEmpleadoVacunacion = DatoEmpleadoVacunacion.builder().datoEmpleado(datoEmpleado).vacunaciones(vacunas).build();
    
            return ResponseEntity.ok(datoEmpleadoVacunacion);
            
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<DatoEmpleado> crearDatosEmpleado(@Validated @RequestBody DatoEmpleado datoEmpleado, BindingResult result){
        if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Message.formatMessage(result));
        }

        DatoEmpleado newDatoEmpleado = datoEmpleadoService.createDatoEmpleado(datoEmpleado);
        return ResponseEntity.status(HttpStatus.CREATED).body(newDatoEmpleado);
    }
    
    @PostMapping("/vacunacion")
    public ResponseEntity<Vacunacion> InsertarVacunacion(@Validated @RequestBody Vacunacion vacunacion, BindingResult result){
        if (result.hasErrors()) {
            String resulError = Message.formatMessage(result);
            System.out.println(resulError);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,resulError);
        }
        Vacunacion newVacunacion = vacunacionService.createVacunacion(vacunacion);

        return ResponseEntity.status(HttpStatus.CREATED).body(newVacunacion);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<DatoEmpleado> actualizarDatosEmpleado(@PathVariable("id") Long id, @Valid @RequestBody DatoEmpleado datoEmpleado){
        DatoEmpleado currentDatoEmpleado = datoEmpleadoService.findDatoEmpleadoById(id);
        if(currentDatoEmpleado != null){
            currentDatoEmpleado = datoEmpleadoService.updateDatoEmpleado(id ,datoEmpleado);
            return ResponseEntity.ok().body(currentDatoEmpleado);
        }
        return ResponseEntity.notFound().build();
    }
}

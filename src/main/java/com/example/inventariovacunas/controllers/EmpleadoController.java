package com.example.inventariovacunas.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.inventariovacunas.errors.Message;
import com.example.inventariovacunas.models.Credencial;
import com.example.inventariovacunas.models.DatoEmpleado;
import com.example.inventariovacunas.models.Empleado;
import com.example.inventariovacunas.models.Vacunacion;
import com.example.inventariovacunas.schemas.CredencialEmpleado;
import com.example.inventariovacunas.services.credencial.ICredencialService;
import com.example.inventariovacunas.services.datoempleado.IDatoEmpleadoService;
import com.example.inventariovacunas.services.empleado.IEmpleadoService;
import com.example.inventariovacunas.services.vacunacion.IVacunacionService;

import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("api/v1/empleados")
@CrossOrigin(origins = "*")
public class EmpleadoController {
    
    @Autowired
    private IEmpleadoService empleadoService;


    @Autowired
    private IVacunacionService vacunacionService;

    @Autowired
    private IDatoEmpleadoService datoEmpleadoService;

    @Autowired 
    private ICredencialService credencialService;

    @GetMapping
    public ResponseEntity<List<Empleado>> listarEmpleados(){
        List<Empleado> empleados = empleadoService.findAllEmpleados(); 
        return empleados.isEmpty() 
                ? ResponseEntity.noContent().build() 
                : ResponseEntity.ok(empleados);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Empleado> obtenerEmpleado(
        @Parameter(required = true, example = "1")
        @PathVariable("id") Long id ){
        Empleado empleado = empleadoService.findEmpleadoById(id);
        if(empleado != null){
            return ResponseEntity.ok(empleado);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Empleado>> filtrarEmpleados(        
        @RequestParam(required = false) Boolean vacunado,
        
        @Parameter(example = "Pfizer")
        @RequestParam(required = false) String vacuna,

        @Parameter(example = "2022-05-17")
        @RequestParam(required = false) String from,

        @Parameter(example = "2022-08-10")
        @RequestParam(required = false) String to ) throws ParseException{
        
        List<Empleado> empleados = null;
        
        if(vacunado != null){
            List<DatoEmpleado> datosEmplados = datoEmpleadoService.findByEstaVacunado(vacunado);
            empleados = datosEmplados.stream()
                                    .map(data -> data.getEmpleado())
                                    .collect(Collectors.toList());
        }

        if(vacuna != null){
            List<Vacunacion> vacunaciones = vacunacionService.findVacunacionesByType(vacuna);
            empleados = vacunaciones.stream()
                                    .map(vacunacion -> vacunacion.getEmpleado())
                                    .collect(Collectors.toList());
        }

        if(from != null && to != null){
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            Date dateFrom = formatter.parse(from);
            Date dateTo = formatter.parse(to);

            List<Vacunacion> vacunaciones = vacunacionService.findVacunacionesInRange(dateFrom, dateTo);
            empleados = vacunaciones.stream()
                                    .map(vacunacion -> vacunacion.getEmpleado())
                                    .collect(Collectors.toList());
        }

        return empleados == null? ResponseEntity.notFound().build() : ResponseEntity.ok(empleados);
    }

    @PostMapping
    public ResponseEntity<CredencialEmpleado> crearEmpleado(@Validated @RequestBody Empleado empleado, BindingResult result){
        if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Message.formatMessage(result));
        }
        Empleado newEmpleado = empleadoService.createEmpleado(empleado);
        Credencial newCredencial = credencialService.generateCredencials(newEmpleado);
        
        CredencialEmpleado credencialEmpleado = CredencialEmpleado.builder().credencial(newCredencial).empleado(newEmpleado).build();

        return ResponseEntity.status(HttpStatus.CREATED).body(credencialEmpleado);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable("id") Long id, @Valid @RequestBody Empleado empleado){
        Empleado currentEmpleado = empleadoService.findEmpleadoById(id);
        if (currentEmpleado != null) {
            empleado.setEmpleadoId(currentEmpleado.getEmpleadoId());
            currentEmpleado = empleadoService.updateEmpleado(empleado);
            return ResponseEntity.ok().body(currentEmpleado);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteEmpleado(@PathVariable("id") Long id) {
        Empleado currentEmpleado = empleadoService.findEmpleadoById(id);
        if(currentEmpleado != null){
            empleadoService.deleteEmpleado(currentEmpleado.getEmpleadoId());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}

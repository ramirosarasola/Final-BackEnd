package com.example.proyectointegradororm.controller;

import com.example.proyectointegradororm.domain.Odontologo;
import com.example.proyectointegradororm.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    private OdontologoService odontologoService;
    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @PostMapping
    public ResponseEntity<Odontologo> registrarOdontologo(@RequestBody Odontologo odontologo){
        return ResponseEntity.of(odontologoService.registrarOdontologo(odontologo));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarOdontologo(@PathVariable Long id){
        return ResponseEntity.of(odontologoService.buscarOdontologo(id));
    }

    @GetMapping(path = "/todos")
    private ResponseEntity<List<Odontologo>> listarOdontologos(){
        return ResponseEntity.of(odontologoService.listarOdontologos());
    }

    @PutMapping
    public ResponseEntity<Odontologo> modificarOdontologo(@RequestBody Odontologo odontologo){
        return ResponseEntity.of(odontologoService.modificarOdontologo(odontologo));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id){
        if(odontologoService.buscarOdontologo(id) != null){
            odontologoService.eliminarOdontologo(id);
            return ResponseEntity.ok("El turno ha sido eliminado correctamente");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }




}

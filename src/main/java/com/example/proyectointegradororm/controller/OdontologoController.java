package com.example.proyectointegradororm.controller;

import com.example.proyectointegradororm.domain.Odontologo;
import com.example.proyectointegradororm.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Odontologo> buscarOdontologo(@RequestParam Long id){
        return ResponseEntity.of(odontologoService.buscarOdontologo(id));
    }

}

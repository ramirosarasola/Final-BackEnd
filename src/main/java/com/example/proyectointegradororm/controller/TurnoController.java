package com.example.proyectointegradororm.controller;

import com.example.proyectointegradororm.domain.Turno;
import com.example.proyectointegradororm.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private TurnoService turnoService;
    @Autowired
    public TurnoController(TurnoService turnoService) {
        this.turnoService = turnoService;
    }
    @PostMapping
    public ResponseEntity<Turno> guardarTurno(@RequestBody Turno turno){
        return ResponseEntity.of(turnoService.guardarTurno(turno));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Turno> buscarTurno(@PathVariable Long id){
        return ResponseEntity.of(turnoService.buscarTurno(id));
    }

    @PutMapping
    public ResponseEntity<Turno> modificarTurno(@RequestBody Turno turno){
        return ResponseEntity.of(turnoService.modificarTurno(turno));
    }

    @DeleteMapping
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id){
        if(turnoService.buscarTurno(id) != null){
            turnoService.eliminarTurno(id);
            return eliminarTurno(id);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}

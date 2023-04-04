package com.example.proyectointegradororm.controller;

import com.example.proyectointegradororm.domain.Odontologo;
import com.example.proyectointegradororm.domain.Paciente;
import com.example.proyectointegradororm.domain.Turno;
import com.example.proyectointegradororm.dto.TurnoDTO;
import com.example.proyectointegradororm.exceptions.ResourceBadRequestException;
import com.example.proyectointegradororm.exceptions.ResourceNotFoundException;
import com.example.proyectointegradororm.service.OdontologoService;
import com.example.proyectointegradororm.service.PacienteService;
import com.example.proyectointegradororm.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private TurnoService turnoService;
    private OdontologoService odontologoService;
    private PacienteService pacienteService;
    @Autowired
    public TurnoController(TurnoService turnoService, OdontologoService odontologoService, PacienteService pacienteService) {
        this.turnoService = turnoService;
        this.odontologoService = odontologoService;
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public ResponseEntity<TurnoDTO> guardarTurno(@RequestBody TurnoDTO turnoDTO) throws ResourceBadRequestException {
        ResponseEntity<TurnoDTO> response;
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPaciente(turnoDTO.getPaciente_id());
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologo(turnoDTO.getOdontologo_id());

        if(pacienteBuscado.isPresent() && odontologoBuscado.isPresent()){
            return ResponseEntity.ok(turnoService.guardarTurno(turnoDTO));
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurnoDTO> buscarTurno(@PathVariable Long id){
        Optional<TurnoDTO> turnoBuscado = turnoService.buscarTurno(id);
        if(turnoBuscado.isPresent()){
            return ResponseEntity.ok(turnoBuscado.get());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/todos")
    public ResponseEntity<List<TurnoDTO>> listarTurnos(){
        return ResponseEntity.ok(turnoService.listarTurnos().get());
    }

    @PutMapping
    public ResponseEntity<TurnoDTO> modificarTurno(@RequestBody TurnoDTO turnoDTO){
        Optional<TurnoDTO> turnoBuscado = turnoService.buscarTurno(turnoDTO.getId());
        if(turnoBuscado.isPresent()){
            return ResponseEntity.ok(turnoService.modificarTurno(turnoDTO).get());
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<TurnoDTO> turnoBuscado = turnoService.buscarTurno(id);
        if(turnoBuscado.isPresent()){
            turnoService.eliminarTurno(id);
            return ResponseEntity.ok("El turno ha sido eliminado correctamente");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}

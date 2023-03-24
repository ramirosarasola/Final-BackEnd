package com.example.proyectointegradororm.controller;

import com.example.proyectointegradororm.domain.Paciente;
import com.example.proyectointegradororm.repository.PacienteRespository;
import com.example.proyectointegradororm.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private PacienteService pacienteService;
    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService= pacienteService;
    }

    @PostMapping
    public ResponseEntity<Paciente> registrarPaciente(@RequestBody Paciente paciente){
        return ResponseEntity.of(pacienteService.registrarPaciente(paciente));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPaciente(@PathVariable Long id){
        return ResponseEntity.of(pacienteService.buscarPaciente(id));
    }
    @GetMapping(path = "/todos")
    public ResponseEntity<List<Paciente>> listarPacientes(){
        return ResponseEntity.of(pacienteService.listarPacientes());
    }
    @PutMapping
    public ResponseEntity<Paciente> modificarPaciente(@RequestBody Paciente paciente){
        return ResponseEntity.of(pacienteService.modificarPaciente(paciente));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id){
        if(pacienteService.buscarPaciente(id) != null){
            pacienteService.eliminarPaciente(id);
            return ResponseEntity.ok("El paciente ha sido eliminado correctamente");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}

package com.example.proyectointegradororm.service;

import com.example.proyectointegradororm.domain.Paciente;
import com.example.proyectointegradororm.repository.PacienteRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    private PacienteRespository pacienteRespository;
    @Autowired
    public PacienteService(PacienteRespository pacienteRespository) {
        this.pacienteRespository = pacienteRespository;
    }
    public Optional<Paciente> buscarPaciente(Long id){
        return pacienteRespository.findById(id);
    }

    public Optional<List<Paciente>> listarPacientes(){
        return Optional.of(pacienteRespository.findAll());
    }




}

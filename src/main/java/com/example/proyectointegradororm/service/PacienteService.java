package com.example.proyectointegradororm.service;

import com.example.proyectointegradororm.domain.Paciente;
import com.example.proyectointegradororm.repository.PacienteRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
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

    public Paciente registrarPaciente(Paciente paciente){
        return pacienteRespository.save(paciente);
    }

    public Optional<Paciente> modificarPaciente(Paciente paciente){
        Optional<Paciente> optionalPaciente = pacienteRespository.findById(paciente.getId());
        if (optionalPaciente.isPresent()) {
            Paciente pacienteModificado = optionalPaciente.get();
            pacienteModificado.setApellido(paciente.getApellido()); // Modifica el apellido
            pacienteModificado.setNombre(paciente.getNombre()); // Modifica el nombre
            pacienteModificado.setDni(paciente.getDni()); // Modifica el dni
            pacienteModificado.setFechaIngreso(paciente.getFechaIngreso()); // Modifica el fechaIngreso
            pacienteModificado.setDomicilio(paciente.getDomicilio()); // Modifica el domicilio
            pacienteModificado.setEmail(paciente.getEmail()); // Modifica el email

            return Optional.of(pacienteRespository.save(pacienteModificado)); // Actualiza la entidad existente en la base de datos
        }
        return Optional.empty();
    }

    public Optional<String> eliminarPaciente(Long id){
        pacienteRespository.deleteById(id);
        return Optional.of("El paciente ha sido eliminado correctamente");
    }

    public Optional<Paciente> buscarPorEmail(String email){
        return pacienteRespository.findByEmail(email);
    }



}

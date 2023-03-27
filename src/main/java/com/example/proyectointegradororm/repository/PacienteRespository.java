package com.example.proyectointegradororm.repository;

import com.example.proyectointegradororm.domain.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PacienteRespository extends JpaRepository<Paciente, Long> {
    Optional<Paciente> findByEmail(String email);
}

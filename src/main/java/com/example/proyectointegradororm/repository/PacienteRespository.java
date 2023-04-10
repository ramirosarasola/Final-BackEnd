package com.example.proyectointegradororm.repository;

import com.example.proyectointegradororm.domain.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PacienteRespository extends JpaRepository<Paciente, Long> {
    Optional<Paciente> findByEmail(String email);
}

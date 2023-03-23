package com.example.proyectointegradororm.repository;

import com.example.proyectointegradororm.domain.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRespository extends JpaRepository<Paciente, Long> {
}

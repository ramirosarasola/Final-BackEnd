package com.example.proyectointegradororm.repository;

import com.example.proyectointegradororm.domain.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TurnoRespository extends JpaRepository<Turno, Long> {
}

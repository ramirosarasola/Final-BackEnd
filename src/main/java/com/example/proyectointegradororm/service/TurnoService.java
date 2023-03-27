package com.example.proyectointegradororm.service;

import com.example.proyectointegradororm.domain.Turno;
import com.example.proyectointegradororm.repository.TurnoRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TurnoService {
    private TurnoRespository turnoRespository;
    @Autowired
    public TurnoService(TurnoRespository turnoRespository) {
        this.turnoRespository = turnoRespository;
    }

    public Optional<Turno> buscarTurno(Long id){
        return turnoRespository.findById(id);
    }
    public Optional<Turno> guardarTurno(Turno turno){
        return Optional.of(turnoRespository.save(turno));
    }
    public Optional<String> eliminarTurno(Long id){
        turnoRespository.deleteById(id);
        return Optional.of("El turno ha sido eliminado");
    }

    public Optional<Turno> modificarTurno(Turno turno){
        return Optional.of(turnoRespository.save(turno));
    }


}

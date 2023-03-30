package com.example.proyectointegradororm.service;

import com.example.proyectointegradororm.domain.Odontologo;
import com.example.proyectointegradororm.domain.Turno;
import com.example.proyectointegradororm.repository.OdontologoRepository;
import com.example.proyectointegradororm.repository.TurnoRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {
    private OdontologoRepository odontologoRepository;
    protected TurnoRespository turnoRespository;

    @Autowired
    public OdontologoService(OdontologoRepository odontologoRepository, TurnoRespository turnoRespository) {
        this.odontologoRepository = odontologoRepository;
        this.turnoRespository = turnoRespository;

    }

    public Optional<Odontologo> buscarOdontologo(Long id){
        return odontologoRepository.findById(id);
    }

    public Optional<List<Odontologo>> listarOdontologos(){
        return Optional.of(odontologoRepository.findAll());
    }

    public Optional<String> eliminarOdontologo(Long id){
        Odontologo odontologo = odontologoRepository.findById(id).get();
        List<Turno> turnoList =  turnoRespository.findAll();

        for (Turno turno : turnoList) {
            if(turno.getOdontologo().getId() == odontologo.getId()){
                turnoRespository.delete(turno);
                odontologoRepository.deleteById(id);
                return Optional.of("El Odontologo ha sido eliminado correctamente y el turno asignado ha sido cancelado");
            }
        }
        return Optional.of("El Odontologo ha sido eliminado correctamente");
    }

    public Optional<Odontologo> registrarOdontologo(Odontologo odontologo){
        return Optional.of(odontologoRepository.save(odontologo));
    }

    public Optional<Odontologo> modificarOdontologo(Odontologo odontologo){
        Optional<Odontologo> odontologoViejoOptional = odontologoRepository.findById(odontologo.getId());
        if(odontologoViejoOptional.isPresent()){
            Odontologo odontologoViejo = odontologoViejoOptional.get();
            odontologoRepository.delete(odontologoViejo);
            Odontologo odontologoActualizado =  odontologoRepository.save(odontologo);
            return Optional.of(odontologoActualizado);
        }else{
         return Optional.empty();
        }
    }



}

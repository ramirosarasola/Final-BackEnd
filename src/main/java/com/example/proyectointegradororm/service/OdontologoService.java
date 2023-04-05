package com.example.proyectointegradororm.service;

import com.example.proyectointegradororm.domain.Odontologo;
import com.example.proyectointegradororm.domain.Turno;
import com.example.proyectointegradororm.exceptions.ResourceBadRequestException;
import com.example.proyectointegradororm.exceptions.ResourceNotFoundException;
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
        Optional<Odontologo> odontologoOptional = odontologoRepository.findById(id);
        if(odontologoOptional.isPresent()){
            return odontologoOptional;
        }else{
            return Optional.empty();
        }
    }

    public Optional<List<Odontologo>> listarOdontologos(){
        return Optional.of(odontologoRepository.findAll());
    }

    public String eliminarOdontologo(Long id) throws ResourceNotFoundException{

        Optional<Odontologo> odontologoOptional = odontologoRepository.findById(id);

        if(odontologoOptional.isPresent()){
            Odontologo odontologo = odontologoOptional.get();
            List<Turno> turnoList =  turnoRespository.findAll();

            for (Turno turno : turnoList) {
                if(turno.getOdontologo().getId() == odontologo.getId()){
                    turnoRespository.delete(turno);
                    odontologoRepository.deleteById(id);
                    return "El Odontologo ha sido eliminado correctamente y el turno asignado ha sido cancelado";
                }
            }
        }else{
            throw new ResourceNotFoundException("Error. El paciente que desea eliminar no existe");
        }
        odontologoRepository.deleteById(id);
        return "El Odontologo ha sido eliminado correctamente";
    }

    public Odontologo registrarOdontologo(Odontologo odontologo) throws ResourceBadRequestException{
        if(odontologo.getNombre().isEmpty() || odontologo.getApellido().isEmpty() || odontologo.getMatricula().isEmpty()){
            throw new ResourceBadRequestException("Error. Debe ingresar correctamente los datos.");
        }
        return odontologoRepository.save(odontologo);
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

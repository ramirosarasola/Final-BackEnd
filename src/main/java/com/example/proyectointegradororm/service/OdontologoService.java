package com.example.proyectointegradororm.service;

import com.example.proyectointegradororm.domain.Odontologo;
import com.example.proyectointegradororm.domain.Turno;
import com.example.proyectointegradororm.exceptions.ResourceBadRequestException;
import com.example.proyectointegradororm.exceptions.ResourceNotFoundException;
import com.example.proyectointegradororm.repository.OdontologoRepository;
import com.example.proyectointegradororm.repository.TurnoRespository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {

    private final static Logger LOGGER = Logger.getLogger(OdontologoService.class.getName());
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
            LOGGER.info("El odontologo ha sido encontrado exitosamente.");
            return odontologoOptional;
        }else{
            LOGGER.error("Error. El odontologo solicitado no existe.");
            return Optional.empty();
        }
    }

    public Optional<List<Odontologo>> listarOdontologos(){
        LOGGER.info("Se listan todos los odontologos disponibles.");
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
            throw new ResourceNotFoundException("Error. El Odontologo que desea eliminar no existe");
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
            LOGGER.warn("Se ha modificado el odontologo con id: " + odontologo.getId());
            Odontologo odontologoViejo = odontologoViejoOptional.get();
            odontologoRepository.delete(odontologoViejo);
            Odontologo odontologoActualizado =  odontologoRepository.save(odontologo);
            return Optional.of(odontologoActualizado);
        }else{
         return Optional.empty();
        }
    }
}

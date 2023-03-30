package com.example.proyectointegradororm.service;

import com.example.proyectointegradororm.domain.Odontologo;
import com.example.proyectointegradororm.domain.Paciente;
import com.example.proyectointegradororm.domain.Turno;
import com.example.proyectointegradororm.dto.TurnoDTO;
import com.example.proyectointegradororm.repository.TurnoRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {
    private TurnoRespository turnoRespository;
    @Autowired
    public TurnoService(TurnoRespository turnoRespository) {
        this.turnoRespository = turnoRespository;
    }

    public Optional<TurnoDTO> buscarTurno(Long id){
        Optional<Turno> turnoBuscado = turnoRespository.findById(id);
        if(turnoBuscado.isPresent()){
            return  Optional.of(TurnoTODTO(turnoBuscado.get()));
        }else{
            return Optional.empty();
        }
    }

    public Optional<List<TurnoDTO>> listarTurnos(){
        List<TurnoDTO> turnoListDTO = new ArrayList<>();
        List<Turno> turnoList = turnoRespository.findAll();
        if(turnoList.size() > 0){
            for (Turno turno : turnoList) {
                turnoListDTO.add(TurnoTODTO(turno));
            }
        }
        return Optional.of(turnoListDTO);
    }

    public TurnoDTO guardarTurno(TurnoDTO turno){
        return TurnoTODTO(turnoRespository.save(DTOtoTurno(turno)));
    }
    public Optional<String> eliminarTurno(Long id){
        Optional<Turno> turnoBuscado = turnoRespository.findById(id);
        if(turnoBuscado.isPresent()){
            turnoRespository.delete(turnoBuscado.get());
            return Optional.of("El turno ha sido eliminado correctamente");
        }else{
            return Optional.empty();
        }
    }

    public Optional<TurnoDTO> modificarTurno(TurnoDTO turno){
        turnoRespository.save(DTOtoTurno(turno));
        return Optional.ofNullable(turno);
    }
    private Turno DTOtoTurno(TurnoDTO turnoDTO){
        Turno turno = new Turno();
        Paciente paciente = new Paciente();
        Odontologo odontologo = new Odontologo();

        turno.setId(turnoDTO.getId());
        turno.setFechaTurno(turnoDTO.getFechaTurno());

        paciente.setId(turnoDTO.getPaciente_id());
        paciente.setNombre(turnoDTO.getNombre_paciente());

        odontologo.setId(turnoDTO.getOdontologo_id());
        odontologo.setNombre(turnoDTO.getNombre_odontologo());

        turno.setOdontologo(odontologo);
        turno.setPaciente(paciente);
        return turno;
    }

    private TurnoDTO TurnoTODTO(Turno turno){
        TurnoDTO turnoDTO = new TurnoDTO();

        turnoDTO.setId(turno.getId());
        turnoDTO.setFechaTurno(turno.getFechaTurno());
        turnoDTO.setOdontologo_id(turno.getOdontologo().getId());
        turnoDTO.setPaciente_id(turno.getPaciente().getId());
        turnoDTO.setNombre_paciente(turno.getPaciente().getNombre());
        turnoDTO.setNombre_odontologo(turno.getOdontologo().getNombre());

        return turnoDTO;
    }

}

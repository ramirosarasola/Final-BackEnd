package com.example.proyectointegradororm.domain;

import com.example.proyectointegradororm.dto.TurnoDTO;
import com.example.proyectointegradororm.exceptions.ResourceBadRequestException;
import com.example.proyectointegradororm.repository.TurnoRespository;
import com.example.proyectointegradororm.service.OdontologoService;
import com.example.proyectointegradororm.service.PacienteService;
import com.example.proyectointegradororm.service.TurnoService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class TurnoTest {
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    public void registrarTurnoTest() throws ResourceBadRequestException {
        Odontologo odontologo = new Odontologo();
        odontologo.setApellido("Sarasola");
        odontologo.setNombre("Ramiro");
        odontologo.setMatricula("646");

        odontologoService.registrarOdontologo(odontologo);

        Domicilio miCasa = new Domicilio();
        miCasa.setProvincia("Buenos Aires");
        miCasa.setLocalidad("Benavidez");
        miCasa.setNumero(5043);
        miCasa.setCalle("Italia");

        Paciente paciente = new Paciente();
        paciente.setApellido("Nini");
        paciente.setNombre("Braian");
        paciente.setDni(4564);
        paciente.setEmail("ramiro@gmail.com");
        paciente.setFechaIngreso(LocalDate.of(2022,12,11));
        paciente.setDomicilio(miCasa);

        pacienteService.registrarPaciente(paciente);

        Turno turno = new Turno();

        turno.setPaciente(paciente);
        turno.setOdontologo(odontologo);
        turno.setFechaTurno(LocalDate.of(2022,12,12));
        TurnoDTO turnoDTO = turnoService.TurnoTODTO(turno);

        TurnoDTO turnoRegistrado = turnoService.guardarTurno(turnoDTO);

        assertEquals(1l, turnoRegistrado.getId());

    }
}
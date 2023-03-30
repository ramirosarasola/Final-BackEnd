package com.example.proyectointegradororm.service;

import com.example.proyectointegradororm.domain.Domicilio;
import com.example.proyectointegradororm.domain.Paciente;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // Con esta anotacion le asignamos el orden en el cual se tienen que ejecutar los tests
@SpringBootTest
class PacienteServiceTest {
    @Autowired
    private PacienteService pacienteService;

    @Test
    @Order(1)
    public void guardarPacienteTest(){
        Domicilio miCasa = new Domicilio();
        miCasa.setProvincia("Buenos Aires");
        miCasa.setLocalidad("Benavidez");
        miCasa.setNumero(5043);
        miCasa.setCalle("Italia");

        Paciente paciente = new Paciente();
        paciente.setNombre("Ramiro");
        paciente.setApellido("Sarasola");
        paciente.setEmail("ramiro@sarasola.com");
        paciente.setFechaIngreso(LocalDate.of(2000,10,28));
        paciente.setDni(41758545);
        paciente.setDomicilio(miCasa);

        Paciente pacienteGuardado = pacienteService.registrarPaciente(paciente);

        assertEquals(1L, pacienteGuardado.getId());
    }
    @Test
    @Order(2)
    public void buscarPorIdTest(){
        Long id = 1L;
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPaciente(id);
        assertNotNull(pacienteBuscado);
    }


}
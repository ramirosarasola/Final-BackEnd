package com.example.proyectointegradororm.service;

import com.example.proyectointegradororm.domain.Odontologo;
import com.example.proyectointegradororm.domain.Paciente;
import com.example.proyectointegradororm.exceptions.ResourceBadRequestException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class OdontologoServiceTest {
    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    public void registrarOdontologoTest() throws ResourceBadRequestException {
        Odontologo odontologo = new Odontologo();
        odontologo.setApellido("Sarasola");
        odontologo.setNombre("Ramiro");
        odontologo.setMatricula("4175");

        Odontologo odontologoRegistrado = odontologoService.registrarOdontologo(odontologo);
        assertEquals(1L, odontologoRegistrado.getId());

    }

    @Test
    @Order(2)
    public void buscarPorIdTest(){
        Long id = 1L;
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologo(id);
        assertNotNull(odontologoBuscado);
    }























}
package com.example.proyectointegradororm.service;

import com.example.proyectointegradororm.domain.Odontologo;
import com.example.proyectointegradororm.domain.Paciente;
import com.example.proyectointegradororm.exceptions.ResourceBadRequestException;
import com.example.proyectointegradororm.exceptions.ResourceNotFoundException;
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
    public void buscarPorIdTest() {
        Long id = 1L;
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologo(id);
        assertNotNull(odontologoBuscado);
    }

    @Test
    @Order(3)
    public void modificarOdontologoTest() throws ResourceBadRequestException {
        Odontologo odontologo = new Odontologo();
        odontologo.setApellido("Sarasola");
        odontologo.setNombre("Ramiro");
        odontologo.setMatricula("4175");

        Odontologo odontologoRegistrado = odontologoService.registrarOdontologo(odontologo);

        Odontologo odontologo1 = new Odontologo();
        odontologo1.setApellido("Messi");
        odontologo1.setNombre("Lionel");
        odontologo1.setMatricula("123123");


        Optional<Odontologo> odontologoModificado = odontologoService.modificarOdontologo(odontologo);

        assertEquals(3L, odontologoModificado.get().getId());
    }

    @Test
    @Order(4)
    public void eliminarOdontologoTest() throws ResourceNotFoundException {
        odontologoService.eliminarOdontologo(1L);

        assertEquals(1L,1);
    }

    @Test
    @Order(5)
    public void listarOdontologosTest() throws ResourceBadRequestException {
        Odontologo odontologo1 = new Odontologo();
        odontologo1.setApellido("Messi");
        odontologo1.setNombre("Leo");
        odontologo1.setMatricula("123123");
        Odontologo odontologo2 = new Odontologo();
        odontologo2.setApellido("Di Maria");
        odontologo2.setNombre("Fideo");
        odontologo2.setMatricula("123123");
        Odontologo odontologo3 = new Odontologo();
        odontologo3.setApellido("Martinez");
        odontologo3.setNombre("Dibu");
        odontologo3.setMatricula("123123");

        odontologoService.registrarOdontologo(odontologo1);
        odontologoService.registrarOdontologo(odontologo2);
        odontologoService.registrarOdontologo(odontologo3);

        Integer cantOdontologos = odontologoService.listarOdontologos().get().size();

        assertEquals(4, cantOdontologos);
    }

}
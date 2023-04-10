package com.example.proyectointegradororm.service;

import com.example.proyectointegradororm.domain.Usuario;
import com.example.proyectointegradororm.domain.UsuarioRol;
import com.example.proyectointegradororm.repository.UsuarioRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class UsuarioServiceTest {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UsuarioRepository usuarioRepository;


    @Test
    @Order(1)
    public void loadUserByUsernameTest(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String passACifrar1 = "a1s2d3asd";
        String passACifrar2 = "123asd";
        String passCifrada1 = encoder.encode(passACifrar1);
        String passCifrada2 = encoder.encode(passACifrar2);
        Usuario user1 = new Usuario("Ramiro", "Sarasola", "ramiro@gmail.com", passCifrada1, UsuarioRol.ROLE_USER);
        Usuario user2 = new Usuario("Jorge", "Sarasola", "jorge@gmail.com", passCifrada2, UsuarioRol.ROLE_ADMIN);
        usuarioRepository.save(user1);
        usuarioRepository.save(user2);

        assertEquals(3L, user1.getId());
    }

}
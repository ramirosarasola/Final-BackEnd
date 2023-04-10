package com.example.proyectointegradororm.security;

import com.example.proyectointegradororm.domain.Usuario;
import com.example.proyectointegradororm.domain.UsuarioRol;
import com.example.proyectointegradororm.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CargadoraDeDatos implements ApplicationRunner {
    private UsuarioRepository usuarioRepository;
    @Autowired
    public CargadoraDeDatos(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String passACifrar1 = "a1s2d3asd";
        String passACifrar2 = "123asd";
        String passCifrada1 = encoder.encode(passACifrar1);
        String passCifrada2 = encoder.encode(passACifrar2);
        Usuario user1 = new Usuario("Ramiro", "Sarasola", "ramiro@gmail.com", passCifrada1, UsuarioRol.ROLE_USER);
        Usuario user2 = new Usuario("Jorge", "Sarasola", "jorge@gmail.com", passCifrada2, UsuarioRol.ROLE_ADMIN);
        usuarioRepository.save(user1);
        usuarioRepository.save(user2);
    }
}

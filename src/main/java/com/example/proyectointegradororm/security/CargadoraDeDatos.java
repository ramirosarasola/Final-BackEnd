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
        String passACifrar = "a1s2d3asd";
        String passCifrada = encoder.encode(passACifrar);
        Usuario usuarioAInsertar = new Usuario("Ramiro", "Sarasola", "ramiro.sarasola@gmail.com", passCifrada, UsuarioRol.ROLE_USER);
        usuarioRepository.save(usuarioAInsertar);
    }
}

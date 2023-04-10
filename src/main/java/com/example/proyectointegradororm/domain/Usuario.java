package com.example.proyectointegradororm.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nombre;
    @Column
    private String apellido;
    @Column
    private String email;
    @Column
    private String password;
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<UsuarioRol> setUserRol = new HashSet<>();

    public Usuario(String nombre, String apellido, String email, String password,UsuarioRol... userRols) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        if (setUserRol!=null){
            setUserRol.addAll(Arrays.asList(userRols));
        }
    }

    public Usuario() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> grantedAuthority = new HashSet<>();
        for (UsuarioRol rol : setUserRol) {
            grantedAuthority.add(new SimpleGrantedAuthority(rol.name()));
        }
        return grantedAuthority;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return id;
    }
}

package com.example.proyectointegradororm.security;

import com.example.proyectointegradororm.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private BCryptPasswordEncoder BCencoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/index.html").hasRole("USER")
                .antMatchers("/index.html").hasRole("ADMIN")
                .antMatchers("/buscarTurno.html").hasRole("USER")
                .antMatchers("/altaTurno.html").hasRole("USER")
                .antMatchers("/altaOdontologo.html").hasRole("ADMIN")
                .antMatchers("/listarOdontologo.html").hasRole("ADMIN")
                .antMatchers("/altaPaciente.html").hasRole("ADMIN")
                .antMatchers("/listarPaciente.html").hasRole("ADMIN")
                .and()
                .formLogin()
                .and()
                .logout();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(usuarioService);
        provider.setPasswordEncoder(BCencoder);
        return provider;
    }
}
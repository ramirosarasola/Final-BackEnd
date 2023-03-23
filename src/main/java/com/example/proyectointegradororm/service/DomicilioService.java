package com.example.proyectointegradororm.service;

import com.example.proyectointegradororm.domain.Domicilio;
import com.example.proyectointegradororm.repository.DomicilioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
@Service
public class DomicilioService {
    private DomicilioRepository domicilioRepository;
    @Autowired
    public DomicilioService(DomicilioRepository domicilioRepository) {
        this.domicilioRepository = domicilioRepository;
    }

    public Optional<Domicilio> buscarDomicilio(Long id){
        return domicilioRepository.findById(id);
    }

    public Optional<List<Domicilio>> listarDomicilios(){
        return Optional.of(domicilioRepository.findAll());
    }

    public Optional<Domicilio> modificarDomicilio(Domicilio domicilio){
        Optional<Domicilio> domicilioViejoOptional = domicilioRepository.findById(domicilio.getId());
        if(domicilioViejoOptional.isPresent()){
            Domicilio domicilioViejo = domicilioViejoOptional.get();
            domicilioRepository.delete(domicilioViejo);
            Domicilio domicilioActualizado = domicilioRepository.save(domicilio);
            return Optional.ofNullable(domicilioActualizado);
        }
        return Optional.empty();
    }

    public Optional<Domicilio> registrarDomicilio(Domicilio domicilio){
        return Optional.of(domicilioRepository.save(domicilio));
    }

}

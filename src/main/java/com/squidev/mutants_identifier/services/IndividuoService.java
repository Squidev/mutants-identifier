package com.squidev.mutants_identifier.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.squidev.mutants_identifier.dtos.DTOIndividuo;
import com.squidev.mutants_identifier.entities.Domicilio;
import com.squidev.mutants_identifier.entities.Individuo;
import com.squidev.mutants_identifier.entities.Mutante;
import com.squidev.mutants_identifier.repositories.IndividuoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.function.EntityResponse;

/**
 * IndividuoService
 */
public class IndividuoService {

    @Autowired
    IndividuoRepository repository;

    @Transactional
	public List<DTOIndividuo> findall() throws Exception{

        List<Individuo> individuos = repository.findAll();
        List<DTOIndividuo> dtosIndividuo = new ArrayList<DTOIndividuo>();
        try{
            for (Individuo i : individuos) {
                DTOIndividuo dto = new DTOIndividuo();
                dto.setId(i.getId());
                dto.setNombre(i.getNombre());
                dto.setApellido(i.getApellido());
                dto.setDni(i.getDni());
                dto.setCalle(i.getDomicilio().getCalle());
                dto.setNumCalle(i.getDomicilio().getNumeroCalle());
                dtosIndividuo.add(dto);
            }
            return dtosIndividuo;
        } catch (Exception e) {
            throw new Exception();
        }
    }
    
    @Transactional
    public DTOIndividuo findById(Long id) throws Exception {
        Optional<Individuo> optionalIndividuo = repository.findById(id);
        DTOIndividuo dto = new DTOIndividuo();
        try {
            Individuo individuo = optionalIndividuo.get();
            dto.setId(individuo.getId());
            dto.setNombre(individuo.getNombre());
            dto.setApellido(individuo.getApellido());
            dto.setDni(individuo.getDni());
            dto.setCalle(individuo.getDomicilio().getCalle());
            dto.setNumCalle(individuo.getDomicilio().getNumeroCalle());

            return dto;
        } catch (Exception e) {
            throw new Exception();
        }               
    }

    @Transactional
    public DTOIndividuo save(DTOIndividuo dto) throws Exception {
        
        Domicilio domicilio = new Domicilio();
        domicilio.setCalle(dto.getCalle());
        domicilio.setNumeroCalle(dto.getCalle());
        if (true) {
            Mutante mutante = new Mutante();
            mutante.setNombre(dto.getNombre());
            mutante.setApellido(dto.getApellido());
            mutante.setEdad(dto.getEdad());
            mutante.setDni(dto.getDni());
            mutante.setDomicilio(domicilio);
            return repository.save(mutante);
            
        } else {
            Humano humano = new Humano();
            humano.setNombre(dto.getNombre());
            humano.setApellido(dto.getApellido());
            humano.setEdad(dto.getEdad());
            humano.setDni(dto.getDni());
            humano.setDomicilio(domicilio);
            return repository.save(humano);
        }
    }


}
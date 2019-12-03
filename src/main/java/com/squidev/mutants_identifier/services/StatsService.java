package com.squidev.mutants_identifier.services;

import javax.transaction.Transactional;

import com.squidev.mutants_identifier.dtos.DTOStats;
import com.squidev.mutants_identifier.repositories.DnaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * StatsService
 */

@Service
public class StatsService {
    
    @Autowired
    DnaRepository repository;

    @Transactional
    public DTOStats getStats() {
        DTOStats dto = new DTOStats();
        //dto.setCount_mutant_dna(repository.getMutantCount());
        //dto.setCount_human_dna(repository.getHumanCount());
       // dto.setRatio(dto.getCount_mutant_dna()/dto.getCount_human_dna());
        return dto;
    }
}
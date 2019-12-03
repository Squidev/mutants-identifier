package com.squidev.mutants_identifier.services;

import java.util.Optional;

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
        Optional<Object> mutantCount = repository.getMutantCount();
        Optional<Object> humanCount = repository.getHumanCount();
        Optional<Object> ratio = repository.getMutantRatio();
        if (mutantCount.isPresent()) {
			dto.setCount_mutant_dna((Long) mutantCount.get());
        }
        if (humanCount.isPresent()) {
			dto.setCount_human_dna((Long) humanCount.get());
		}
		if (ratio.isPresent()) {
			dto.setRatio((Double) ratio.get());
		}
        return dto;
    }
}
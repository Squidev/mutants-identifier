package com.squidev.mutants_identifier.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.squidev.mutants_identifier.entities.Mutante;
/**
 * IndividuoRepository
 */
public interface MutanteRepository extends JpaRepository<Mutante, Long> {

    
}
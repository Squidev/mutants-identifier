package com.squidev.mutants_identifier.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.squidev.mutants_identifier.entities.Dna;;
/**
 * MutantRepository
 */
@Repository
public interface DnaRepository extends JpaRepository<Dna, Long> {
    //public int getMutantCount();
    //public int getHumanCount();
}
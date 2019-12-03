package com.squidev.mutants_identifier.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.squidev.mutants_identifier.entities.Dna;
/**
 * MutantRepository
 */
@Repository
public interface DnaRepository extends JpaRepository<Dna, Integer> {
    @Query("SELECT COUNT(*) as mutant_count FROM Dna d where d.isMutant=true")
	Optional<Object> getMutantCount();
    
 /*    @Query(value = "SELECT COUNT(*) as mutant_count FROM dna d", nativeQuery = true)
	Optional<Object> getMutantCount(); */
	
	@Query("SELECT COUNT(*) as human_count FROM Dna d WHERE d.isMutant=false")
	Optional<Object> getHumanCount();
	
	@Query("SELECT AVG(isMutant) as avg_mutant FROM Dna d WHERE d.isMutant=false or d.isMutant=true")
	Optional<Object> getMutantRatio();

	@Query("FROM Dna d WHERE d.dnaData=:dna")
	Optional<Dna> findByDnaString(@Param("dna") String dna);
}
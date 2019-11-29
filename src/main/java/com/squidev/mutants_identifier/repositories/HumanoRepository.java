package com.squidev.mutants_identifier.repositories;

import com.squidev.mutants_identifier.entities.Humano;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * HumanoRepository
 */
public interface HumanoRepository extends JpaRepository<Humano,Long> {

    
}
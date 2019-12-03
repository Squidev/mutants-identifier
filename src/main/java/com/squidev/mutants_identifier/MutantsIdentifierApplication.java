package com.squidev.mutants_identifier;

import javax.persistence.EntityManager;

import com.squidev.mutants_identifier.entities.Dna;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MutantsIdentifierApplication {

	public static void main(String[] args) {
		SpringApplication.run(MutantsIdentifierApplication.class, args);	
	}

}

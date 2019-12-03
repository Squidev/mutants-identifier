package com.squidev.mutants_identifier;

import javax.persistence.EntityManager;

import com.squidev.mutants_identifier.entities.Dna;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MutantsIdentifierApplication {

	public static void main(String[] args) {
		SpringApplication.run(MutantsIdentifierApplication.class, args);

		Dna dnaEntity = new Dna();
		String[] dna = {"SFEF","DEGD","DEGR","DGRY"};
		dnaEntity.setDnaData(dna);
		System.out.println("Antes de entrar en el for");
		for (String string : dnaEntity.getDnaData()) {
			System.out.println("Dentro del for");
			System.out.println(string); 	
		}
		
	}

}

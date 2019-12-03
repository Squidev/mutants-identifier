package com.squidev.mutants_identifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Optional;

import com.squidev.mutants_identifier.controllers.DnaController;
import com.squidev.mutants_identifier.entities.Dna;
import com.squidev.mutants_identifier.repositories.DnaRepository;
import com.squidev.mutants_identifier.services.DnaService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource(locations = "classpath:application.properties")
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Execution(ExecutionMode.CONCURRENT)
public class DnaRepositoryTest {

	@Autowired
	DnaRepository dnaRepository;

    @Autowired
    DnaService dnaService;

	@Autowired
    DnaController dnaController;
    
    @Order(1)
    @DisplayName("Injected components are not null")
	@Test
	void injectedComponentsAreNotNull() {
        assertThat(dnaController).isNotNull();
        assertThat(dnaService).isNotNull();
		assertThat(dnaRepository).isNotNull();
		System.out.println(1);
	}

	@Order(2)
	@DisplayName("Save on Repository Test")
	@RepeatedTest(3)
	public void saveDnaTest(TestInfo testInfo) throws Exception {
		String[] dnaArray = {"ATGCGA", "CAGTGC", "TAATGT", "AGACAG", "GCGTCA", "TCACTG"};
		Dna dnaEntity = new Dna();
		dnaEntity.setDnaData(dnaArray);
		dnaEntity.setIsMutant(false);
		dnaRepository.save(dnaEntity);
		assertThat(dnaEntity.getDnaData()).isNotNull();
		System.out.println(3);
	}

	@Order(3)
	@DisplayName("Find on Repository Test")
	@RepeatedTest(value = 5, name = "{displayName} ---> {currentRepetition}")
	public void findDnaTest(TestInfo testInfo) throws Exception {
		String[] dnaArray = {"ATGCGA", "CAGTGC", "TAATGT", "AGACAG", "GCGTCA", "TCACTG"};
		Dna dnaEntity = new Dna();
        dnaEntity.setDnaData(dnaArray);
            
        Optional<Dna> optionalEntity = dnaRepository.findByDnaString(Arrays.toString(dnaArray));
		assertTrue(optionalEntity.isPresent());
		assertThat(optionalEntity.get()).isNotNull();
		System.out.println(2);
	}
}
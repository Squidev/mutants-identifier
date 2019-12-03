package com.squidev.mutants_identifier;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.squidev.mutants_identifier.services.DnaService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource(locations = "classpath:application.properties")

@SpringBootTest
@AutoConfigureMockMvc
@Execution(ExecutionMode.CONCURRENT)
public class DnaServiceTest {
	
	@Autowired
	DnaService dnaService;

	@DisplayName("DnaService Test: Mutant DNA")
	@RepeatedTest(2)
	public void mutantDnaTest(final TestInfo testInfo) throws Exception {
		final String[] dnaArray = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
		assertTrue(dnaService.isMutant(dnaArray));
	}
	
	@DisplayName("DnaService Test: Human DNA")
	@RepeatedTest(2)
	public void humanDnaTest(final TestInfo testInfo) throws Exception {
	    final String[] dnaArray = {"ATGCGA", "CAGTGC", "TAATGT", "AGACAG", "GCGTCA", "TCACTG"};
        assertFalse(dnaService.isMutant(dnaArray));		
	}
}
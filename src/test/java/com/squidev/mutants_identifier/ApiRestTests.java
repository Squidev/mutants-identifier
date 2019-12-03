package com.squidev.mutants_identifier;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@TestPropertySource(locations = "classpath:application.properties")

@SpringBootTest
@AutoConfigureMockMvc
@Execution(ExecutionMode.CONCURRENT)
public class ApiRestTests {

	@Autowired
	private MockMvc mvc;

	@DisplayName("Api Test: Mutant DNA")
	@RepeatedTest(30)
	void isMutantTest(TestInfo testInfo) throws Exception {
		String[] dnaArray = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
		MvcResult result = mvc.perform(post("/mutant/").content("{\"dna\":"+asJsonString(dnaArray)+"}") 
							.contentType(MediaType.APPLICATION_JSON))
							.andExpect(status().isOk())
							.andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
							.andExpect(content().string("OK"))
							.andDo(MockMvcResultHandlers.print())
							.andExpect(status().isOk())
							.andReturn();
		String content = result.getResponse().getContentAsString();
		System.out.println("JSON send: {\"dna\":"+asJsonString(dnaArray)+"} Api Return ===> "+content);
	}

	@DisplayName("Api Test: Human DNA")
	@RepeatedTest(30)
	void isHumanTest(TestInfo testInfo) throws Exception {
		String[] dnaArray = {"ATGCGA", "CAGTGC", "TAATGT", "AGACAG", "GCGTCA", "TCACTG"};
		MvcResult result = mvc.perform(post("/mutant/").content("{\"dna\":"+asJsonString(dnaArray)+"}")
							.contentType(MediaType.APPLICATION_JSON))
							.andExpect(status().isForbidden())
							.andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
							.andExpect(content().string("FORBIDDEN"))
							.andDo(MockMvcResultHandlers.print())
							.andExpect(status().isForbidden())
							.andReturn();
		String content = result.getResponse().getContentAsString();
		System.out.println("JSON send: {\"dna\":"+asJsonString(dnaArray)+"} Api Return ===> "+content);
	}

	@DisplayName("Api Test: Stats")
	@RepeatedTest(30)
	public void statsTest(TestInfo testInfo) throws Exception {
		MvcResult result = mvc.perform(get("/stats").contentType(MediaType.APPLICATION_JSON))
							.andExpect(status().isOk())
							.andExpect(content()
							.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
							.andExpect(status().isOk())
							.andExpect(jsonPath("$.count_mutant_dna", is(notNullValue())))
							.andExpect(jsonPath("$.count_human_dna", is(notNullValue())))
							.andExpect(jsonPath("$.ratio", is(notNullValue())))
							.andDo(MockMvcResultHandlers.print())
							.andReturn();
		String content = result.getResponse().getContentAsString();
		System.out.println("Api Return ===> "+content);
	}

	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}

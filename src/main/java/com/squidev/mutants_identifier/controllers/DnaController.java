package com.squidev.mutants_identifier.controllers;

import com.squidev.mutants_identifier.services.DnaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * IndividuoController
 */
@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@RequestMapping(path = "mutant")
public class DnaController {

    @Autowired
    private DnaService service;

    public DnaController() {
    }

    public ResponseEntity isMutant(@RequestBody String[] dna) throws Exception {
        
        try {
            boolean response = service.isMutant(dna);
            if (response) {
                return ResponseEntity.status(HttpStatus.OK).body("OK");
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("");
            }
        } catch (Exception e) {
            throw new Exception();
        }

    }
}
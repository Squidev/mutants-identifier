package com.squidev.mutants_identifier.controllers;

import java.util.Map;

import javax.transaction.Transactional;

import com.squidev.mutants_identifier.services.DnaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    @PostMapping(path = "/", consumes = "application/json")
    @Transactional
    public ResponseEntity isMutant(@RequestBody Map<String,String[]> dnaMap) throws Exception {
        String[] dna = dnaMap.get("dna");
        try {
            boolean response = service.isMutant(dna);
            if (response) {
                return ResponseEntity.status(HttpStatus.OK).body("OK");
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("FORBIDDEN");
            }
        } catch (Exception e) {
            throw new Exception();
        }
    }
}
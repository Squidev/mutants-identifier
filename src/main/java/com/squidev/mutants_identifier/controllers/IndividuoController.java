package com.squidev.mutants_identifier.controllers;

import com.squidev.mutants_identifier.services.IndividuoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * IndividuoController
 */
@RestController
public class IndividuoController {

    @Autowired
    private IndividuoService service;

    public IndividuoController() {
    }

    public ResponseEntity getAll() throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.findall());
        } catch (Exception e) {
            throw new Exception();
        }
    }

    public ResponseEntity postIndividuo() {
        return ResponseEntity.status(HttpStatus.OK).body(service.)
    }

    public ResponseEntity putIndividuo() {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateIndividuo());
    }

    public ResponseEntity deleteIndividuo() {
        return ResponseEntity.status(HttpStatus.OK).body(service.deleteIndividuo());
    }

    public ResponseEntity getOne() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getOne());
    }
}
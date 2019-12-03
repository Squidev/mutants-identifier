package com.squidev.mutants_identifier.controllers;

import javax.transaction.Transactional;

import com.squidev.mutants_identifier.services.StatsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * StatsController
 */
@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping(path = "stats")
public class StatsController {

    @Autowired
    StatsService service;

    public StatsController() {
    }
    
    @GetMapping
    @Transactional
    public ResponseEntity getStats() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getStats());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\":\""+e.getMessage()+".\"}");
        }


    }

    
}
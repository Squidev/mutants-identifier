package com.squidev.mutants_identifier.entities;

import javax.persistence.Entity;

@Entity
public class Dna {
    private Long id;
    private char[][] dnaMatrix;
    private boolean isMutant;

    public Dna() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public char[][] getDnaMatrix() {
        return dnaMatrix;
    }

    public void setDnaMatrix(char[][] dnaMatrix) {
        this.dnaMatrix = dnaMatrix;
    }

    public boolean isMutant() {
        return isMutant;
    }

    public void setMutant(boolean isMutant) {
        this.isMutant = isMutant;
    }

    
}
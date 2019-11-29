package com.squidev.mutants_identifier.entities;

import javax.persistence.Entity;

/**
 * Domicilio
 */
@Entity
public class Domicilio {

    private String calle;
    private int numeroCalle;

    public Domicilio() {
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumeroCalle() {
        return numeroCalle;
    }

    public void setNumeroCalle(int numeroCalle) {
        this.numeroCalle = numeroCalle;
    }

    
}
package com.squidev.mutants_identifier.entities;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Dna implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dnaData;
    private boolean isMutant;

    public Dna() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //Gets the dna as an String array, from a single String formatted as "[string1, string2, string3, ...]".
    public String[] getDnaData() {
        //Get rid of the initial "[" and the final "]".
        String dnaDataNoBrackets = dnaData.substring(1, dnaData.length()-1);
        //Use the String.class split method to split the string using ", " as delimiting character.
        String[] dna = dnaDataNoBrackets.split(", ");
        return dna;
    }

    //Sets the dna as a single String, formatted as "[string1, string2, string3, ...]".
    public void setDnaData(String[] dna) {
        System.out.println("Recibiendo array de cadenas:");
        for (String string : dna) {
            //debug: System.out.println(string);
        }
        this.dnaData = Arrays.toString(dna);
        //debug: System.out.println("Almacenada cadena:"+dnaData);
    }

    public boolean isMutant() {
        return isMutant;
    }

    public void setMutant(boolean isMutant) {
        this.isMutant = isMutant;
    }
}
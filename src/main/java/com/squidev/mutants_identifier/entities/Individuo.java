package com.squidev.mutants_identifier.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public abstract class Individuo {
    private Long id;
    private String nombre;
    private String apellido;
    private int edad;
    private int dni;
    @OneToMany(mappedBy = "domicilio")
    @JoinColumn(name = "individuo_fk")
    private Domicilio domicilio;
    private int[][] secuenciaGenetica;

    public Individuo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public int[][] getSecuenciaGenetica() {
        return secuenciaGenetica;
    }

    public void setSecuenciaGenetica(int[][] secuenciaGenetica) {
        this.secuenciaGenetica = secuenciaGenetica;
    }
    
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.proyectoCAFD.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bruno Conte
 */
public class Disciplina {

    private int id;
    private String nom;
    private String descrip;
    
    private List<Clase> clase;
    
    public Disciplina(){
        clase = new ArrayList<>(); //injerencia de tipos
    }
    
    
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the descrip
     */
    public String getDescrip() {
        return descrip;
    }

    /**
     * @param descrip the descrip to set
     */
    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    /**
     * @return the clase
     */
    public List<Clase> getClase() {
        return clase;
    }

    /**
     * @param clase the clase to set
     */
    public void setClase(List<Clase> clase) {
        this.clase = clase;
    }
    
}

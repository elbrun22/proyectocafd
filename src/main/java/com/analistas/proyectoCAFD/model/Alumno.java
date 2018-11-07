/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.proyectoCAFD.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;



/**
 *
 * @author Bruno Conte
 */

@Entity
@Table (name = "alumnos")
public class Alumno {

    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private int id;
    
    @Column(name = "ape")
    private String ape;
    @Column(name = "nom")
    private String nom;
    @Column(name = "tel")
    private String tel;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "alumnos")
    private List <Inscripcion> inscripcionesAlumnos;
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApe() {
        return ape;
    }

    public void setApe(String ape) {
        this.ape = ape;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    
    public List <Inscripcion> getInscripcionesAlumnos() {
        return inscripcionesAlumnos;
    }

    public void setInscripcionesAlumnos(List <Inscripcion> inscripcionesAlumnos) {
        this.inscripcionesAlumnos = inscripcionesAlumnos;
    }
    
    
   

     
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.proyectoCAFD.service;

import com.analistas.proyectoCAFD.model.Alumno;
import java.util.List;

/**
 *
 * @author Bruno Conte
 */
public interface IAlumnosService {
    
    public List<Alumno> buscarTodo();
    
    public Alumno BuscarPorId(int id);
    
    public void save(Alumno alumno);
    
    public Alumno getOne(int id);
	
	public void Borrar(int id);
    
   
}

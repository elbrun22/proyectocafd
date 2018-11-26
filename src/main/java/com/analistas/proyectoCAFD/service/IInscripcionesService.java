/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.proyectoCAFD.service;

import com.analistas.proyectoCAFD.model.Inscripcion;
import java.util.List;

/**
 *
 * @author Bruno Conte
 */
public interface IInscripcionesService {
    
    public List<Inscripcion> buscarTodo();
    
    public Inscripcion BuscarPorId(int id);
    
    public void save(Inscripcion inscripciones);
    
    public void Borrar(int id);
    
}

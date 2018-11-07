/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.proyectoCAFD.service;

import com.analistas.proyectoCAFD.model.Clase;
import java.util.List;

/**
 *
 * @author Bruno Conte
 */
public interface IClasesService {
    
    public List<Clase> buscarTodo();
    
    public Clase BuscarPorId(int id);
    
    public void save(Clase clases);
}

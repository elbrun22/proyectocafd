/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.proyectoCAFD.service;

import com.analistas.proyectoCAFD.model.Noticia;
import java.util.List;

/**
 *
 * @author Bruno Conte
 */
public interface INoticiasService {
        
    public List<Noticia> buscarTodo();
    
    public Noticia BuscarPorId(int id);
    
    public void save(Noticia noticias);
    
    public void Borrar(int id);
}

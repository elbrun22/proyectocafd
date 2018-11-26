/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.proyectoCAFD.service;

import com.analistas.proyectoCAFD.model.Maestro;
import java.io.IOException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Bruno Conte
 */
public interface IMaestrosService {
    
    public List<Maestro> buscarTodo();
    
    public Maestro BuscarPorId(int id);
    
    public void save(Maestro maestros);
    
    public void Borrar(int id);
    
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.proyectoCAFD.service;

import com.analistas.proyectoCAFD.dao.IClasesDao;
import com.analistas.proyectoCAFD.model.Clase;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Bruno Conte
 */
@Service
public class IClasesServiceImpl implements IClasesService{

    @Autowired
    IClasesDao dao;
    
    @Override
    @Transactional(readOnly = true)
   public List<Clase> buscarTodo() {
         return dao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Clase BuscarPorId(int id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void save(Clase clases) {
        dao.save(clases);
    }

    @Override
    @Transactional
    public void Borrar(int id) {
        dao.deleteById(id);
    }
    
}

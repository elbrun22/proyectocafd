/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.proyectoCAFD.service;

import com.analistas.proyectoCAFD.dao.INoticiasDao;
import com.analistas.proyectoCAFD.model.Noticia;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Bruno Conte
 */
@Service
public class INoticiasServiceImpl implements INoticiasService{

    @Autowired
    INoticiasDao dao;
    
    @Override
    @Transactional(readOnly = true)
    public List<Noticia> buscarTodo() {
        return dao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Noticia BuscarPorId(int id) {
         return dao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void save(Noticia noticias) {
        dao.save(noticias);
    }

    @Override
    @Transactional
    public void Borrar(int id) {
        dao.deleteById(id);
    }
    
}

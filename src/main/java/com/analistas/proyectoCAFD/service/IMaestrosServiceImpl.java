/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.proyectoCAFD.service;

import com.analistas.proyectoCAFD.dao.IMaestrosDao;
import com.analistas.proyectoCAFD.model.Maestro;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Bruno Conte
 */
@Service
public class IMaestrosServiceImpl implements IMaestrosService{

    @Autowired
    IMaestrosDao dao;
    
    @Override
    @Transactional(readOnly = true)
    public List<Maestro> buscarTodo() {
        return dao.findAll();
    }
    
    
    @Override
    @Transactional(readOnly = true)
    public Maestro BuscarPorId(int id) {
         return dao.findById(id).orElse(null);
    }

    
    @Override
    @Transactional
    public void save(Maestro maestro) {
        dao.save(maestro);
    }

    @Override
    @Transactional
    public void Borrar(int id) {
        dao.deleteById(id);
    }

   

    
    
}

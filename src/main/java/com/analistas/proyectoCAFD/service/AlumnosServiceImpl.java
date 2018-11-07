/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.proyectoCAFD.service;

import com.analistas.proyectoCAFD.dao.IAlumnosDao;
import com.analistas.proyectoCAFD.model.Alumno;
import static com.sun.jmx.snmp.SnmpStatusException.readOnly;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Bruno Conte
 */
@Service
public class AlumnosServiceImpl implements IAlumnosService{

    @Autowired
    IAlumnosDao dao;
    
    @Override
    @Transactional(readOnly = true)
    public List<Alumno> buscarTodo() {
        return dao.findAll();
    }
    

    @Override
    @Transactional(readOnly = true)
    public Alumno BuscarPorId(int id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void save(Alumno alumnos) {
        dao.save(alumnos);
    }

   

    @Override
    @Transactional
    public void Borrar(int id) {
        dao.deleteById(id);
    }

    

    @Override
    @Transactional(readOnly = true)
    public Alumno getOne(int id) {
        return dao.getOne(id);
    }

    
    
    
}

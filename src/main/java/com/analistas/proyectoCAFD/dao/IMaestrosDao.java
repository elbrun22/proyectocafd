/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.proyectoCAFD.dao;

import com.analistas.proyectoCAFD.model.Maestro;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Bruno Conte
 */
public interface IMaestrosDao extends JpaRepository<Maestro, Integer>{
    
}

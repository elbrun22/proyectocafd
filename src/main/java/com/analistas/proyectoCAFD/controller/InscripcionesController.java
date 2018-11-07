/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.proyectoCAFD.controller;

import com.analistas.proyectoCAFD.model.Inscripcion;
import com.analistas.proyectoCAFD.service.IInscripcionesService;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Bruno Conte
 */
@Controller
public class InscripcionesController {
    
    @Autowired
    IInscripcionesService serv1;
    
    @GetMapping({"/", "/inscripciones"})
    public String home(Map m) throws SQLException{
        
        //carreraDao = new CarreraDao();
        List<Inscripcion> lista = serv1.buscarTodo();
        m.put("titulo", "Lista de Inscriptos");
        m.put("titulo_tabla", "Listado de Inscripciones");
        m.put("inscripciones", lista);
        
        m.put("cant_carreras", lista.size());
        
        
        
        return "inscripciones";
    }
}

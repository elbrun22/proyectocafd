/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.proyectoCAFD.controller;

import com.analistas.proyectoCAFD.model.Clase;
import com.analistas.proyectoCAFD.model.Evento;
import com.analistas.proyectoCAFD.service.IClasesService;
import com.analistas.proyectoCAFD.service.IEventoService;
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
public class HomeController {
    
     @Autowired
    IEventoService serv5;
    @Autowired
    IClasesService serv6;
    
    @GetMapping({"/", "/home"})
    public String home(Map m) throws SQLException{
        
        List<Evento> lista = serv5.buscarTodo();
        List<Clase> lista2 = serv6.buscarTodo();
        m.put("eventos", lista);
        m.put("clases", lista2);
        m.put("titulo", "CAFD");
        
        
        
        
        return "index";
    }
    
}

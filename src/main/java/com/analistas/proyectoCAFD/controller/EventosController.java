/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.proyectoCAFD.controller;

import com.analistas.proyectoCAFD.model.Evento;
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
public class EventosController {

    @Autowired
    IEventoService serv4;
    
    @GetMapping({"/eventos"})
    public String home(Map m) throws SQLException{
        
        //carreraDao = new CarreraDao();
        List<Evento> lista = serv4.buscarTodo();
        m.put("titulo", "Lista de Eventos");
        m.put("titulo_tabla", "Listado de Eventos");
        m.put("eventos", lista);
        
        m.put("cant_carreras", lista.size());
        
        
        
        return "eventos";
    }
    
}

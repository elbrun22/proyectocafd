/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.proyectoCAFD.controller;

import com.analistas.proyectoCAFD.model.Clase;
import com.analistas.proyectoCAFD.model.Evento;
import com.analistas.proyectoCAFD.model.Maestro;
import com.analistas.proyectoCAFD.service.IClasesService;
import com.analistas.proyectoCAFD.service.IEventoService;
import com.analistas.proyectoCAFD.service.IMaestrosService;
import com.analistas.proyectoCAFD.service.IUploadFileService;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Bruno Conte
 */
@Controller
public class HomeController {
    
     @Autowired
    IEventoService serv3;
    @Autowired
    IClasesService serv4;
    @Autowired
    IMaestrosService serv2;
    
    @Autowired
    IUploadFileService upl;
    
    @GetMapping({"/home","/"})
    public String home(Map m) throws SQLException{
        
        List<Evento> lista = serv3.buscarTodo();
        List<Clase> lista2 = serv4.buscarTodo();
        List<Maestro> lista3 = serv2.buscarTodo();
        m.put("eventos", lista);
        m.put("clases", lista2);
        m.put("maestros", lista3);
        m.put("titulo", "CAFD");
        
        return "index";
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.proyectoCAFD.controller;

import com.analistas.proyectoCAFD.model.Clase;
import com.analistas.proyectoCAFD.model.Inscripcion;
import com.analistas.proyectoCAFD.service.IClasesService;
import com.analistas.proyectoCAFD.service.IInscripcionesService;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Bruno Conte
 */

@Controller
public class InscripcionesController {
    
    @Autowired
    IInscripcionesService serv1;
    
    @Autowired
    IClasesService serv4;
    
    @GetMapping({"/inscripciones"})
    public String home(Map m) throws SQLException{
        
        Inscripcion inscripciones = new Inscripcion();
       List<Clase> lista = serv4.buscarTodo();
        m.put("inscripciones", inscripciones);
        m.put("clases", lista);
        
        
        return "inscripciones";
    }
    
    


    
    @RequestMapping(value = "/formInscripciones", method = RequestMethod.POST)
    public String guardar(@Valid Inscripcion inscripciones, BindingResult result, Model model, SessionStatus status, RedirectAttributes flash) {

       
        
        serv1.save(inscripciones);
        status.setComplete();

        flash.addFlashAttribute("success", "Su formulario ha sido enviado con éxito!");
        
        return "redirect:inscripciones";
    }
    
   
}

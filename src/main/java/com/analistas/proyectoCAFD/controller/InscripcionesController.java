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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Bruno Conte
 */

@Controller
@SessionAttributes("inscripcion")
public class InscripcionesController {
    
    @Autowired
    IInscripcionesService serv1;
    
    @Autowired
    IClasesService serv4;
    
    
    
    
    
    @RequestMapping("/inscripcionClases/{id}")
    public String inscripcionesclases(@PathVariable(value = "id") Integer id, Map<String, Object> model, RedirectAttributes flash) {
        
        Clase clases = serv4.BuscarPorId(id);
        
       Inscripcion inscripcion = new Inscripcion();
       inscripcion.setClases(clases);
        
        model.put("inscripcion", inscripcion);
        
        return "inscripcionClases";
    }
    
    


    
    @RequestMapping(value = "/inscripcionClases", method = RequestMethod.POST)
    public String guardar(@Valid Inscripcion inscripciones, BindingResult result, Model model, SessionStatus status, RedirectAttributes flash) {

       
        
        serv1.save(inscripciones);
        status.setComplete();

        flash.addFlashAttribute("success", "Su formulario ha sido enviado con éxito!");
        
        return "redirect:/ClasesHome";
    }
    
   @GetMapping({"/inscripciones"})
    public String lista(Map m) throws SQLException{
        
       
       List<Inscripcion> lista = serv1.buscarTodo();
        m.put("inscripciones", lista);
       
        
        
        return "inscripciones";
    }
    
    
    @RequestMapping(value = "/borrarI/{id}")
    public String eliminar(@PathVariable(value = "id") int id, RedirectAttributes flash) {

        if (id > 0) {
            Inscripcion inscripcion = serv1.BuscarPorId(id);

            serv1.Borrar(id);
            flash.addFlashAttribute("success", "Inscripcion eliminada con éxito!");

        }
        return "redirect:/inscripciones";
    }
}

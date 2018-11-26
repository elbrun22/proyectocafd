/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.proyectoCAFD.controller;


import com.analistas.proyectoCAFD.model.Inscripcion;
import com.analistas.proyectoCAFD.model.Login;
import com.analistas.proyectoCAFD.service.IInscripcionesService;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class AdminController {
    
    @Autowired
    IInscripcionesService serv1;
    
   @GetMapping({"/inscripcionesT"})
    public String listar(Map m) throws SQLException {
       
         List<Inscripcion> lista = serv1.buscarTodo();
        m.put("titulo", "General");
        m.put("inscripciones", lista);
        return "admin";
    }
    
//    @RequestMapping(value="/general", method=RequestMethod.POST)
//    public String Login(@ModelAttribute(name="loginForm") Login login, Model model){
//        
//        String nombre = login.getNombre();
//        String contraseña = login.getContraseña();
//        
//        if("admin".equals(nombre) && "admin".equals(contraseña)){
//            return "admin";
//        }
//        
//        model.addAttribute("invalidCredentials", true);
//        
//        return "index";
//    }
    
    @RequestMapping(value = "/borrarI/{id}")
    public String eliminar(@PathVariable(value = "id") int id, RedirectAttributes flash) {

        if (id > 0) {
            Inscripcion inscripcion = serv1.BuscarPorId(id);

            serv1.Borrar(id);
            flash.addFlashAttribute("success", "Inscripcion eliminada con éxito!");

        }
        return "redirect:/inscripcionesT";
    }
    
    
}

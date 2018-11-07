/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.proyectoCAFD.controller;

import com.analistas.proyectoCAFD.model.Maestro;
import com.analistas.proyectoCAFD.service.IMaestrosService;
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
@SessionAttributes("maestros")
@Controller
public class MaestrosController {
    
    @Autowired
    IMaestrosService serv2;
    
    @GetMapping({"/maestros"})
    public String home(Map m) throws SQLException{
        
        //carreraDao = new CarreraDao();
        List<Maestro> lista = serv2.buscarTodo();
        m.put("titulo", "Lista de Maestros");
        m.put("titulo_tabla", "Listado de Maestros");
        m.put("maestros", lista);
        
        m.put("cant_carreras", lista.size());
        
        
        
        return "maestros";
    }
    
     @GetMapping("/formMaestros")
    public String nuevo (Map m) {
        Maestro maestro = new Maestro();
        
        
        m.put("titulo", "Nuevo Maestro");
        m.put("maestro", maestro);
        
        
        return "formMaestros";
        
    }

    @RequestMapping(value = "/formMaestros/{id}")
    public String editar(@PathVariable(value = "id") int id, Map<String, Object> model, RedirectAttributes flash) {

        Maestro maestro = null;

        if (id > 0) {
            maestro = serv2.BuscarPorId(id);
            if (maestro == null) {
                flash.addFlashAttribute("error", "El ID del maestro no existe en la BBDD!");
                return "redirect:/maestros";
            }
        } else {
            flash.addFlashAttribute("error", "El ID del maestro no puede ser cero!");
            return "redirect:/maestros";
        }
        model.put("maestro", maestro);
        model.put("titulo", "Editar maestro");
        return "formMaestros";
    }

    @RequestMapping(value = "/formMaestros", method = RequestMethod.POST)
    public String guardar(@Valid Maestro maestro, BindingResult result, Model model, SessionStatus status) {

        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de Maestro");
            return "formMaestros";
        }

        serv2.save(maestro);
        status.setComplete();

        return "redirect:maestros";
    }

    @RequestMapping(value = "/borrarM/{id}")
    public String eliminar(@PathVariable(value = "id") int id, RedirectAttributes flash) {

        if (id > 0) {
            Maestro maestro = serv2.BuscarPorId(id);

            serv2.Borrar(id);
            flash.addFlashAttribute("success", "Maestro eliminado con éxito!");

        }
        return "redirect:/maestros";
    }
}


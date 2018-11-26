/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.proyectoCAFD.controller;

import com.analistas.proyectoCAFD.model.Alumno;
import com.analistas.proyectoCAFD.service.IAlumnosService;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author matia
 */
@SessionAttributes("alumno")
@Controller
public class AlumnosController {

    @Autowired
    IAlumnosService serv;

    @GetMapping({"/alumnos"})
    public String listar(Map m) throws SQLException {

        List<Alumno> alumnos = serv.buscarTodo();

        m.put("titulo", "Listado de alumnos");
        m.put("alumnos", alumnos);
        return "alumnos";
    }

    @GetMapping("/form")
    public String nuevo (Map m) {
        Alumno alumno = new Alumno();
        
        
        m.put("titulo", "Nuevo Alumno");
        m.put("alumno", alumno);
        
        
        return "form";
        
    }

    @RequestMapping(value = "/form/{id}")
    public String editar(@PathVariable(value = "id") int id, Map<String, Object> model, RedirectAttributes flash) {

        Alumno alumno = null;

        if (id > 0) {
            alumno = serv.getOne(id);
            if (alumno == null) {
                flash.addFlashAttribute("error", "El ID del cliente no existe en la BBDD!");
                return "redirect:/alumnos";
            }
        } else {
            flash.addFlashAttribute("error", "El ID del cliente no puede ser cero!");
            return "redirect:/alumnos";
        }
        model.put("alumno", alumno);
        model.put("titulo", "Editar alumno");
        return "form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String guardar(@Valid Alumno alumno, BindingResult result, Model model, SessionStatus status, RedirectAttributes flash) {

        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de Alumno");
            return "form";
        }

        flash.addFlashAttribute("warning", "Alumno editado con éxito!");
        
        serv.save(alumno);
        status.setComplete();

        return "redirect:alumnos";
    }

    @RequestMapping(value = "/borrar/{id}")
    public String eliminar(@PathVariable(value = "id") int id, RedirectAttributes flash) {

        if (id > 0) {
            Alumno alumno = serv.getOne(id);

            serv.Borrar(id);
            flash.addFlashAttribute("success", "Alumno eliminado con éxito!");

        }
        return "redirect:/alumnos";
    }
}

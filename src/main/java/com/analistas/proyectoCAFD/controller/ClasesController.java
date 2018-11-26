/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.proyectoCAFD.controller;

import com.analistas.proyectoCAFD.model.Clase;
import com.analistas.proyectoCAFD.service.IClasesService;
import com.analistas.proyectoCAFD.service.IUploadFileService;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Bruno Conte
 */
@SessionAttributes("clase")
@Controller
public class ClasesController {
    @Autowired
    IClasesService serv4;
    
    @Autowired
    IUploadFileService upl;
    
    @GetMapping(value = "/uploadsC/{filename:.+}")
	public ResponseEntity<Resource> verFotoC(@PathVariable String filename) {

		Resource recurso = null;

		try {
			recurso = upl.load(filename);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
				.body(recurso);
	}

	@GetMapping(value = "/verClases/{id}")
	public String ver(@PathVariable(value = "id") Integer id, Map<String, Object> model, RedirectAttributes flash) {

		Clase clase = serv4.BuscarPorId(id);
		if (clase == null) {
			flash.addFlashAttribute("error", "La clase no existe en la base de datos");
			return "redirect:/clases";
		}

		model.put("clase", clase);
		model.put("titulo", "Detalle clase: " + clase.getNom());
		return "verClases";
	}
    
    @GetMapping({"/clases"})
    public String home(Map m) throws SQLException{
        
        //carreraDao = new CarreraDao();
        List<Clase> lista = serv4.buscarTodo();
        m.put("titulo", "Lista de Clases");
        m.put("titulo_tabla", "Listado de Clases");
        m.put("clases", lista);
        
        return "clases";
    }
    
     @GetMapping("/formClases")
    public String nuevo (Map m) {
        Clase clase = new Clase();
        
        
        m.put("titulo", "Nueva Clase");
        m.put("clase", clase);
        
        
        return "formClases";
        
    }
    
    @GetMapping({"/ClasesHome"})
    public String claseshome(Map m) throws SQLException{
        
        //carreraDao = new CarreraDao();
        List<Clase> lista = serv4.buscarTodo();
        m.put("titulo", "Lista de Clases");
        m.put("titulo_tabla", "Listado de Clases");
        m.put("clases", lista);
        
        return "ClasesHome";
    }
    

    @RequestMapping(value = "/formClases/{id}")
    public String editar(@PathVariable(value = "id") int id, Map<String, Object> model, RedirectAttributes flash) {

        Clase clase = null;

        if (id > 0) {
            clase = serv4.BuscarPorId(id);
            if (clase == null) {
                flash.addFlashAttribute("error", "El ID del clase no existe en la BBDD!");
                return "redirect:/clases";
            }
        } else {
            flash.addFlashAttribute("error", "El ID de la clase no puede ser cero!");
            return "redirect:/clases";
        }
        model.put("clase", clase);
        model.put("titulo", "Editar clase");
        return "formClases";
    }

    @RequestMapping(value = "/formClases", method = RequestMethod.POST)
    public String guardar(@Valid Clase clase, BindingResult result, Model model,@RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status) {

        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de Clase");
            return "formClases";
        }
        
        if (!foto.isEmpty()) {

			if (clase.getId() > 0 && clase.getFoto() != null
					&& clase.getFoto().length() > 0) {

				upl.delete(clase.getFoto());
			}

			String uniqueFilename = null;
			try {
				uniqueFilename = upl.copy(foto);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");

			clase.setFoto(uniqueFilename);

		}

        flash.addFlashAttribute("warning", "Clase editada con éxito!");
        
        serv4.save(clase);
        status.setComplete();

        return "redirect:clases";
    }

    @RequestMapping(value = "/borrarC/{id}")
    public String eliminar(@PathVariable(value = "id") int id, RedirectAttributes flash) {

        if (id > 0) {
            Clase clase = serv4.BuscarPorId(id);

            serv4.Borrar(id);
            flash.addFlashAttribute("success", "Clase eliminado con éxito!");

        }
        return "redirect:/clases";
    }
    
}
    


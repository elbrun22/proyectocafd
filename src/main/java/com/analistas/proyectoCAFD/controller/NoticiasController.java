/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.proyectoCAFD.controller;

import com.analistas.proyectoCAFD.model.Noticia;
import com.analistas.proyectoCAFD.service.INoticiasService;
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
@SessionAttributes("noticia")
@Controller
public class NoticiasController {
    
    @Autowired
    INoticiasService serv5;
    
    @Autowired
    IUploadFileService upl;
    
    @GetMapping(value = "/uploadsN/{filename:.+}")
	public ResponseEntity<Resource> verFotoN(@PathVariable String filename) {

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

	@GetMapping(value = "/verNoticias/{id}")
	public String ver(@PathVariable(value = "id") Integer id, Map<String, Object> model, RedirectAttributes flash) {

		Noticia noticia = serv5.BuscarPorId(id);
		if (noticia == null) {
			flash.addFlashAttribute("error", "la noticia no existe en la base de datos");
			return "redirect:/noticias";
		}

		model.put("noticia", noticia);
		model.put("titulo", "Detalle noticia: " + noticia.getNombre());
		return "verNoticias";
	}
    
    @GetMapping({"/noticias"})
    public String home(Map m) throws SQLException{
        
        //carreraDao = new CarreraDao();
        List<Noticia> lista = serv5.buscarTodo();
        m.put("titulo", "Lista de Noticias");
        m.put("titulo_tabla", "Listado de Noticias");
        m.put("noticias", lista);
        
       
        return "noticias";
    }
    
    
    
     @GetMapping("/formNoticias")
    public String nuevo (Map m) {
        Noticia noticia = new Noticia();
        
        
        m.put("titulo", "Nueva Noticia");
        m.put("noticia", noticia);
        
        
        return "formNoticias";
        
    }

    @RequestMapping(value = "/formNoticias/{id}")
    public String editar(@PathVariable(value = "id") int id, Map<String, Object> model, RedirectAttributes flash) {

        Noticia noticia = null;

        if (id > 0) {
            noticia = serv5.BuscarPorId(id);
            if (noticia == null) {
                flash.addFlashAttribute("error", "El ID de la noticia no existe en la BBDD!");
                return "redirect:/noticias";
            }
        } else {
            flash.addFlashAttribute("error", "El ID de la noticia no puede ser cero!");
            return "redirect:/noticias";
        }
        model.put("noticia", noticia);
        model.put("titulo", "Editar noticia");
        return "formNoticias";
    }

    @RequestMapping(value = "/formNoticias", method = RequestMethod.POST)
    public String guardar(@Valid Noticia noticia, BindingResult result, Model model,@RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status) {

        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de Noticia");
            return "formNoticias";
        }
        
        if (!foto.isEmpty()) {

			if (noticia.getId() > 0 && noticia.getFoto() != null
					&& noticia.getFoto().length() > 0) {

				upl.delete(noticia.getFoto());
			}

			String uniqueFilename = null;
			try {
				uniqueFilename = upl.copy(foto);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");

			noticia.setFoto(uniqueFilename);

		}

        flash.addFlashAttribute("warning", "Noticia editada con éxito!");
        
        serv5.save(noticia);
        status.setComplete();

        return "redirect:noticias";
    }

    @RequestMapping(value = "/borrarN/{id}")
    public String eliminar(@PathVariable(value = "id") int id, RedirectAttributes flash) {

        if (id > 0) {
            Noticia noticia = serv5.BuscarPorId(id);

            serv5.Borrar(id);
            flash.addFlashAttribute("success", "Noticia eliminada con éxito!");

        }
        return "redirect:/noticias";
    }
    
}

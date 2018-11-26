/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.proyectoCAFD.controller;

import com.analistas.proyectoCAFD.model.Evento;
import com.analistas.proyectoCAFD.service.IEventoService;
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
@SessionAttributes("evento")
@Controller
public class EventosController {
    
    @Autowired
    IEventoService serv3;
    
    @Autowired
    IUploadFileService upl;
    
    @GetMapping(value = "/uploadsE/{filename:.+}")
	public ResponseEntity<Resource> verFotoE(@PathVariable String filename) {

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

	@GetMapping(value = "/verEventos/{id}")
	public String ver(@PathVariable(value = "id") Integer id, Map<String, Object> model, RedirectAttributes flash) {

		Evento evento = serv3.BuscarPorId(id);
		if (evento == null) {
			flash.addFlashAttribute("error", "El evento no existe en la base de datos");
			return "redirect:/eventos";
		}

		model.put("evento", evento);
		model.put("titulo", "Detalle evento: " + evento.getNom());
		return "verEventos";
	}
    
    @GetMapping({"/eventos"})
    public String home(Map m) throws SQLException{
        
        //carreraDao = new CarreraDao();
        List<Evento> lista = serv3.buscarTodo();
        m.put("titulo", "Lista de Eventos");
        m.put("titulo_tabla", "Listado de Eventos");
        m.put("eventos", lista);
        
       
        return "eventos";
    }
    
    @GetMapping({"/EventosHome"})
    public String eventoshome(Map m) throws SQLException{
        
        //carreraDao = new CarreraDao();
        List<Evento> lista = serv3.buscarTodo();
        m.put("titulo", "Lista de Eventos");
        m.put("titulo_tabla", "Listado de Eventos");
        m.put("eventos", lista);
        
       
        return "EventosHome";
    }
    
     @GetMapping("/formEventos")
    public String nuevo (Map m) {
        Evento evento = new Evento();
        
        
        m.put("titulo", "Nuevo Evento");
        m.put("evento", evento);
        
        
        return "formEventos";
        
    }

    @RequestMapping(value = "/formEventos/{id}")
    public String editar(@PathVariable(value = "id") int id, Map<String, Object> model, RedirectAttributes flash) {

        Evento evento = null;

        if (id > 0) {
            evento = serv3.BuscarPorId(id);
            if (evento == null) {
                flash.addFlashAttribute("error", "El ID del evento no existe en la BBDD!");
                return "redirect:/eventos";
            }
        } else {
            flash.addFlashAttribute("error", "El ID del evento no puede ser cero!");
            return "redirect:/eventos";
        }
        model.put("evento", evento);
        model.put("titulo", "Editar evento");
        return "formEventos";
    }

    @RequestMapping(value = "/formEventos", method = RequestMethod.POST)
    public String guardar(@Valid Evento evento, BindingResult result, Model model,@RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status) {

        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de Evento");
            return "formEventos";
        }
        
        if (!foto.isEmpty()) {

			if (evento.getId() > 0 && evento.getFoto() != null
					&& evento.getFoto().length() > 0) {

				upl.delete(evento.getFoto());
			}

			String uniqueFilename = null;
			try {
				uniqueFilename = upl.copy(foto);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");

			evento.setFoto(uniqueFilename);

		}

        flash.addFlashAttribute("warning", "Evento editado con éxito!");
        
        serv3.save(evento);
        status.setComplete();

        return "redirect:eventos";
    }

    @RequestMapping(value = "/borrarE/{id}")
    public String eliminar(@PathVariable(value = "id") int id, RedirectAttributes flash) {

        if (id > 0) {
            Evento evento = serv3.BuscarPorId(id);

            serv3.Borrar(id);
            flash.addFlashAttribute("success", "Evento eliminado con éxito!");

        }
        return "redirect:/eventos";
    }
    
}

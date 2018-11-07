/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.proyectoCAFD.controller;


import com.analistas.proyectoCAFD.model.Login;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
    
    @RequestMapping(value="/login", method=RequestMethod.GET)
    public String getLoginForm(){
       
        return "login";
    }
    
    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String Login(@ModelAttribute(name="loginForm") Login login, Model model){
        
        String nombre = login.getNombre();
        String contraseña = login.getContraseña();
        
        if("admin".equals(nombre) && "admin".equals(contraseña)){
            return "admin";
        }
        
        model.addAttribute("invalidCredentials", true);
        
        return "index";
    }
}

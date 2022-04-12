/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Usuario
 */

@Controller
@RequestMapping("/")
public class PortalControlador {
    
    @GetMapping("/")
    public String index() {
        return "index.html";
    }
    
    @GetMapping("/Cargar_Autores")
    public String Cargar_Autores() {
        return "Cargar_Autores.html";
    }
    
    @GetMapping("/Listar_Autores")
    public String Listar_Autores() {
        return "Listar_Autores.html";
    }
    
    @GetMapping("/Cargar_Editoriales")
    public String Cargar_Editoriales() {
        return "Cargar_Editoriales.html";
    }
    
    @GetMapping("/Listar_Editoriales")
    public String Listar_Editoriales() {
        return "Listar_Editoriales.html";
    }
    
    @GetMapping("/Cargar_Libros")
    public String Cargar_Libros() {
        return "Cargar_Libros.html";
    }
    
    @GetMapping("/Listar_Libros")
    public String Listar_Libros() {
        return "Listar_Libros.html";
    }
    
    
}

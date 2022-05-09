/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controladores;

import com.example.demo.entidades.Autor;
import com.example.demo.entidades.Editorial;
import com.example.demo.entidades.Libro;
import com.example.demo.errores.ErrorServicio;
import com.example.demo.repositorios.AutorRepositorio;
import com.example.demo.servicios.AutorServicio;
import com.example.demo.servicios.EditorialServicio;
import com.example.demo.servicios.LibroServicio;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Usuario
 */

@Controller
@RequestMapping("/")
public class PortalControlador {
    
    @Autowired
    private AutorServicio autorServicio;
    
    @Autowired
    private EditorialServicio editorialServicio;
    
    @Autowired
    private LibroServicio libroServicio;

    
    @GetMapping("/")
    public String index() {
        return "index.html";
    }
    
    //--------------------------------AUTORES--------------------------------------------------------------------------
    //--------------------------------AUTORES--------------------------------------------------------------------------
    //--------------------------------AUTORES--------------------------------------------------------------------------
    //--------------------------------AUTORES--------------------------------------------------------------------------
    
    @GetMapping("/Cargar_Autores")
    public String Cargar_Autores() {
        return "Cargar_Autores.html";
    }
    
    @PostMapping("/cargar")
    public String cargar(@RequestParam String nombre) {
        
        try {
            autorServicio.cargar(nombre);
        } catch (ErrorServicio ex) {
            Logger.getLogger(PortalControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "Cargar_Autores.html";
        }
        return "index.html";
    }
    
    @GetMapping("/Editar_Autores/{id}")
    public String editar_Autor(RedirectAttributes redirectAttributes, @PathVariable("id") String id, ModelMap modelo) {
        Autor autor;        
        try{
            autor = autorServicio.buscarPorId(id);
            modelo.put("nombre", autor.getNombre());
            return "Editar_Autores.html";
        } catch (ErrorServicio ex){
            Logger.getLogger(PortalControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "index.html";
        }        
    }
        
    @PostMapping("/Editar_Autores")
    public String editar_Autor(RedirectAttributes redirectAttributes, ModelMap modelo, String id, @RequestParam(required = false) String nombreAutor){
        Autor autor = autorServicio.buscarPorId2(id).get();
        try{
            modelo.put("nombreAutor", autor.getNombre());
            autorServicio.modificar(id, nombreAutor);            
            
        }catch(ErrorServicio ex){
            modelo.put("error", ex.getMessage());
            redirectAttributes.addAttribute("id", id);
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
            return "Editar_Autores.html";
        }
        modelo.put("mensaje", "Autor modificado con exito");
        return "index.html";
    } 
 
    @GetMapping("/Listar_Autores")
    public String Listar_Autores(ModelMap modelo) {
        List<Autor> listaAutor = autorServicio.listarTodos();
        modelo.addAttribute("autores", listaAutor);
        return "Listar_Autores.html";
    }
    
    /*
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable String id, ModelMap modelo) {
        modelo.put("autor", autorServicio.buscarPorId(id));        
        return "Editar_Autores.html";
    }
    
    @PostMapping("/editar/{id}")
    public String editar(@PathVariable String id, @RequestParam String nombre, ModelMap modelo) {
        try {
            autorServicio.modificar(id, nombre);
            //modelo.put("exito", "Modificacion exitosa");            
            return "Editar_Autores.html";
        } catch (ErrorServicio ex) {
            //modelo.put("error", "Falto algun dato");
            return "Editar_Autores.html";
        }            
    }
    */
      
    //--------------------------------EDITORIALES--------------------------------------------------------------------------
    //--------------------------------EDITORIALES--------------------------------------------------------------------------
    //--------------------------------EDITORIALES--------------------------------------------------------------------------
    //--------------------------------EDITORIALES--------------------------------------------------------------------------
    
    @GetMapping("/Cargar_Editoriales")
    public String Cargar_Editoriales() {
        return "Cargar_Editoriales.html";
    }
    
    @PostMapping("/cargarEd")
    public String cargarEd(@RequestParam String nombre) {
        
        try {
            editorialServicio.cargar(nombre);
        } catch (ErrorServicio ex) {
            Logger.getLogger(PortalControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "Cargar_Editoriales.html";
        }
        return "index.html";
    }
    
    @GetMapping("/Listar_Editoriales")
    public String Listar_Editoriales(ModelMap modelo) {
        List<Editorial> listaEditorial = editorialServicio.mostrarTodas();
        modelo.addAttribute("editoriales", listaEditorial);
        return "Listar_Editoriales.html";
    }
    
    @GetMapping("/Editar_Editoriales/{id}")
    public String editar_Editoriales(RedirectAttributes redirectAttributes, @PathVariable("id") String id, ModelMap modelo){
       Editorial editorial;        
        try{
            editorial = editorialServicio.buscarPorId(id);
            modelo.put("nombre", editorial.getNombre());
            return "Editar_Editoriales.html";
        } catch (ErrorServicio ex){
            Logger.getLogger(PortalControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "index.html";
        } 
    }
    
    @PostMapping("/Editar_Editoriales")
    public String editar_Editoriales(RedirectAttributes redirectAttributes, ModelMap modelo, String id, @RequestParam(required = false) String nombreEditorial){
        Editorial editorial = editorialServicio.buscarPorId2(id).get();
        try{
            modelo.put("nombreEditorial", editorial.getNombre());
            editorialServicio.modificar(id, nombreEditorial);            
            
        }catch(ErrorServicio ex){
            modelo.put("error", ex.getMessage());
            redirectAttributes.addAttribute("id", id);
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
            return "Editar_Editorial.html";
        }
        modelo.put("mensaje", "Editorial modificado con exito");
        return "index.html";
    } 
     

    
    //--------------------------------LIBROS--------------------------------------------------------------------------
    //--------------------------------LIBROS--------------------------------------------------------------------------
    //--------------------------------LIBROS--------------------------------------------------------------------------
    //--------------------------------LIBROS--------------------------------------------------------------------------
    
    @GetMapping("/Cargar_Libros")
    public String Cargar_Libros(ModelMap modelo) {       
               
        List<Autor> autores = autorServicio.listarTodos();
        modelo.put("autores", autores);
        
        List<Editorial> editoriales = editorialServicio.mostrarTodas();
        modelo.put("editoriales", editoriales);
               
        return "Cargar_Libros.html";
    }
    
    @PostMapping("/cargarLib")
    public String cargarLib(ModelMap modelo, @RequestParam Long ISBN, @RequestParam String titulo, @RequestParam Integer anio, @RequestParam Integer ejemplares, @RequestParam Integer ejemplaresPrestados, @RequestParam Autor autor, @RequestParam Editorial editorial) {
        
        try {
            libroServicio.cargar(ISBN, titulo, anio, ejemplares, ejemplaresPrestados, autor, editorial);
        } catch (ErrorServicio ex) {
            
            modelo.put("error", ex.getMessage());
            Logger.getLogger(PortalControlador.class.getName()).log(Level.SEVERE, null, ex);
            modelo.put("ISBN", ISBN);
            modelo.put("titulo", titulo);
            modelo.put("anio", anio);
            modelo.put("ejemplares", ejemplares);
            modelo.put("ejemplaresPrestados", ejemplaresPrestados);
            modelo.put("autor", autor);
            modelo.put("editorial", editorial);
            
            return "Cargar_Libros.html";
        }
        return "index.html";
    }
    
    @GetMapping("/Listar_Libros")
    public String Listar_Libros(ModelMap modelo) {
        List<Libro> listaLibro = libroServicio.listarTodos();
        modelo.addAttribute("libros", listaLibro);
        return "Listar_Libros.html";
    }
    
    @GetMapping("/Editar_Libros/{id}")
    public String editar_Libros(RedirectAttributes redirectAttributes, @PathVariable("id") String id, ModelMap modelo){
       Libro libro;        
        try{
            libro = libroServicio.buscarPorId(id);
            modelo.put("titulo", libro.getTitulo());
            return "Editar_Libros.html";
        } catch (ErrorServicio ex){
            Logger.getLogger(PortalControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "index.html";
        } 
    }
    
    @PostMapping("/Editar_Libros")
    public String editar_Libro(RedirectAttributes redirectAttributes, ModelMap modelo,@RequestParam String id, @RequestParam Long isbn, 
            @RequestParam String titulo, @RequestParam Integer anio, @RequestParam Integer ejemplares, 
            @RequestParam Integer ejemplaresPrestados, @RequestParam Autor autor, @RequestParam Editorial editorial){
        
        Libro libro = libroServicio.buscarPorId2(id).get();
        try{            
            modelo.put("isbn", libro.getIsbn());
            modelo.put("titulo", libro.getTitulo());
            modelo.put("anio", libro.getAnio());
            modelo.put("ejemplares", libro.getEjemplares());
            modelo.put("ejemplaresPrestados", libro.getEjemplaresPrestados());
            modelo.put("autor", libro.getAutor());
            modelo.put("editorial", libro.getEditorial());
            libroServicio.modificar(id, isbn, titulo, anio, ejemplares, ejemplaresPrestados, autor , editorial);
                   
        }catch(ErrorServicio ex){
            modelo.put("error", ex.getMessage());
            redirectAttributes.addAttribute("id", id);
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
            return "Editar_Libro.html";
        }
        modelo.put("mensaje", "Libro modificado con exito");
        return "index.html";
    } 
}

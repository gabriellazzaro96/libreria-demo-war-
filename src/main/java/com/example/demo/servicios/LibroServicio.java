
package com.example.demo.servicios;

import com.example.demo.entidades.Autor;
import com.example.demo.entidades.Editorial;
import com.example.demo.entidades.Libro;
import com.example.demo.errores.ErrorServicio;
import com.example.demo.repositorios.LibroRepositorio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroServicio {
    
    @Autowired
    private LibroRepositorio libroRepositorio;
    
    //METODO PARA CARGAR UN LIBRO
    public void cargar(Long isbn, String titulo, Integer anio, Integer ejemplares, 
            Integer ejemplaresPresados, Autor autor, Editorial editorial) throws ErrorServicio{
        
        validar(isbn, titulo, anio, ejemplares, ejemplaresPresados, autor, editorial);
        
        Libro libro = new Libro();
        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setAnio(anio);
        libro.setEjemplares(ejemplares);
        libro.setEjemplaresPrestados(ejemplaresPresados);
        libro.setAutor(autor);
        libro.setEditorial(editorial);
        
        libroRepositorio.save(libro);
    }
    
    //METODO PARA MODIFICAR UN LIBRO
    public void modificar(String id, Long isbn, String titulo, Integer anio, Integer ejemplares, 
            Integer ejemplaresPresados, Autor autor, Editorial editorial)throws ErrorServicio{
        
        Optional<Libro> respuesta = libroRepositorio.findById(id);
        if(respuesta.isPresent()){
            Libro libro = respuesta.get();
            libro.setIsbn(isbn);
            libro.setTitulo(titulo);
            libro.setAnio(anio);
            libro.setEjemplares(ejemplares);
            libro.setEjemplaresPrestados(ejemplaresPresados);
            libro.setAutor(autor);
            libro.setEditorial(editorial);
            
            libroRepositorio.save(libro);
        }else{
            throw new ErrorServicio("No se encuentra el libro buscado");
        }        
    }
    
    //METODO PARA DESHABILITAR UN LIBRO
    public void deshabilitar(String id, Boolean alta)throws ErrorServicio{
        
        Optional<Libro> respuesta = libroRepositorio.findById(id);
        if(respuesta.isPresent()){
            Libro libro = respuesta.get();
            libro.setAlta(Boolean.FALSE);
            
            libroRepositorio.save(libro);
        }else{
            throw new ErrorServicio("No se encuentra el libro buscado");
        }        
    }
    
    //METODO PARA REHABILITAR UN LIBRO
    public void rehabilitar(String id, Boolean alta)throws ErrorServicio{
        
        Optional<Libro> respuesta = libroRepositorio.findById(id);
        if(respuesta.isPresent()){
            Libro libro = respuesta.get();
            libro.setAlta(Boolean.TRUE);
            
            libroRepositorio.save(libro);
        }else{
            throw new ErrorServicio("No se encuentra el libro buscado");
        }        
    }
    
    
    //METODO PARA VALIDAR PARAMETROS
    private void validar(Long isbn, String titulo, Integer anio, Integer ejemplares, 
            Integer ejemplaresPresados, Autor autor, Editorial editorial) throws ErrorServicio{
        if(isbn == null){
            throw new ErrorServicio("El ISBN no puede ser nulo");
        }
        if(titulo == null || titulo.isEmpty()){
            throw new ErrorServicio("El Titulo no puede estar vacio");
        }
        if(anio == null){
            throw new ErrorServicio("El año no puede ser nulo");
        }
        if(ejemplares == null || ejemplares<0){
            throw new ErrorServicio("Los ejemplares no pueden ser nulos o menor a cero");
        }
        if(ejemplaresPresados == null || ejemplaresPresados <0){
            throw new ErrorServicio("El año no puede ser nulo o menor a cero");
        }
        if(autor == null){
            throw new ErrorServicio("El Autor no puede estar vacio");
        }
        if(editorial == null){
            throw new ErrorServicio("La editorial no puede estar vacia");
        }
        
    }
    
}

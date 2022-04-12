
package com.example.demo.servicios;

import com.example.demo.entidades.Autor;
import com.example.demo.entidades.Editorial;
import com.example.demo.entidades.Libro;
import com.example.demo.errores.ErrorServicio;
import com.example.demo.repositorios.LibroRepositorio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LibroServicio {
    
    @Autowired
    private LibroRepositorio libroRepositorio;
    
    //METODO PARA CARGAR UN LIBRO
    @Transactional(propagation = Propagation.NESTED)
    public void cargar(Long isbn, String titulo, Integer anio, Integer ejemplares, 
            Integer ejemplaresPresados, Autor autor, Editorial editorial) throws ErrorServicio{
        
        validar(isbn, titulo, anio, ejemplares, ejemplaresPresados, autor, editorial);
        
        Libro libro = new Libro();
        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setAnio(anio);
        libro.setEjemplares(ejemplares);
        libro.setEjemplaresPrestados(ejemplaresPresados);
        libro.setEjemplaresRestantes(ejemplares - ejemplaresPresados);
        libro.setAutor(autor);
        libro.setEditorial(editorial);
        
        libroRepositorio.save(libro);
    }
    
    //METODO PARA MODIFICAR UN LIBRO
    @Transactional(propagation = Propagation.NESTED)
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
    @Transactional(propagation = Propagation.NESTED)
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
    @Transactional(propagation = Propagation.NESTED)
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
     
    //METODO PARA BUSCAR UN LIBRO POR ID
    @Transactional(readOnly = true)
    public void buscarPorId(String id){
        libroRepositorio.buscarPorId(id);
    }
    
    //METODO PARA BUSCAR LIBRO POR SU TITULO
    @Transactional(readOnly = true)
    public void buscarPorNombre(String nombre){
        libroRepositorio.buscarPorNombre(nombre);
    }
    
    //METODO PARA BUSCAR LIBROS POR SU AUTOR
    @Transactional(readOnly = true)
    public void buscarPorAutor(String nombre ){
        libroRepositorio.buscarPorAutor(nombre);
    }
    
    //METODO PARA BUSCAR LIBROS POR SU EDITORIAL
    @Transactional(readOnly = true)
    public void buscarPorEditorial(String nombre){
        libroRepositorio.buscarPorEditorial(nombre);
    }
    
    //METODO PARA BUSCAR LIBROS POR AÑO
    @Transactional(readOnly = true)
    public void buscarPorAnio(Integer anio){
        libroRepositorio.buscarPorAnio(anio);
    }
    
    //METODO PARA BORRAR UN LIBRO
    @Transactional(propagation = Propagation.NESTED)
    public void borrar(String id)throws ErrorServicio{
        
        Optional<Libro> respuesta = libroRepositorio.findById(id);
        if(respuesta.isPresent()){
            Libro libro = respuesta.get();
            libroRepositorio.delete(libro);
            
        }else{
            throw new ErrorServicio("No se encuentra el libro buscado");
        }        
    }
    
    //METODO PARA VALIDAR PARAMETROS
    private void validar(Long isbn, String titulo, Integer anio, Integer ejemplares, 
            Integer ejemplaresPresados, Autor autor, Editorial editorial) throws ErrorServicio{
        if(isbn == null || isbn.toString().isEmpty()){
            throw new ErrorServicio("El ISBN no puede ser nulo");
        }
        if(titulo == null || titulo.isEmpty()){
            throw new ErrorServicio("El Titulo no puede estar vacio");
        }
        if(anio == null || anio.toString().isEmpty()){
            throw new ErrorServicio("El año no puede ser nulo");
        }
        if(ejemplares == null || ejemplares<0){
            throw new ErrorServicio("Los ejemplares no pueden ser nulos o menor a cero");
        }
        if(ejemplaresPresados == null || ejemplaresPresados <0){
            throw new ErrorServicio("El año no puede ser nulo o menor a cero");
        }
        if(autor == null || autor.toString().trim().isEmpty()){
            throw new ErrorServicio("El Autor no puede estar vacio");
        }
        if(editorial == null || autor.toString().trim().isEmpty()){
            throw new ErrorServicio("La editorial no puede estar vacia");
        }
        
    }
    
}

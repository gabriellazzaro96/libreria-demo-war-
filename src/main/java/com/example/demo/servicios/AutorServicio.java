/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.servicios;

import com.example.demo.entidades.Autor;
import com.example.demo.entidades.Libro;
import com.example.demo.errores.ErrorServicio;
import com.example.demo.repositorios.AutorRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AutorServicio {

    //DECLARAMOS EL REPOSITORIO COMO ATRIBUTO
    @Autowired //La variable inicializa el servidor de aplicaciones
    private AutorRepositorio autorRepositorio;

    //METODO PARA CARGAR UN AUTOR
    @Transactional(propagation = Propagation.NESTED)
    public void cargar(String nombre) throws ErrorServicio {

        validar(nombre);

        Autor autor = new Autor(); //creamos el objeto

        autor.setNombre(nombre);//seteamos atributos
        autor.setAlta(Boolean.TRUE);

        autorRepositorio.save(autor);//mediante el repositorio lo cargamos en la DB

    }

    //METODO PARA MODIFICAR UN AUTOR
    @Transactional(propagation = Propagation.NESTED)
    public void modificar(String id, String nombre) throws ErrorServicio {

        validar(nombre);

        Optional<Autor> respuesta = autorRepositorio.findById(id);//Metodo de validación, si es que no encuentra lo que buscamos
        if (respuesta.isPresent()) {

            Autor autor = respuesta.get();
            autor.setNombre(nombre);

            autorRepositorio.save(autor);
        } else {
            throw new ErrorServicio("No se encontro el autor solicitado");
        }
    }

    //METODO PARA DESHABILITAR UN AUTOR
    @Transactional(propagation = Propagation.NESTED)
    public void dehabilitar(String id) throws ErrorServicio {
        Optional<Autor> respuesta = autorRepositorio.findById(id);
        if (respuesta.isPresent()) {

            Autor autor = respuesta.get();
            autor.setAlta(Boolean.FALSE);

            autorRepositorio.save(autor);
        } else {
            throw new ErrorServicio("No se encontro el autor solicitado");
        }
    }

    //METODO PARA REHABILITAR UN AUTOR
    @Transactional(propagation = Propagation.NESTED)
    public void rehabilitar(String id) throws ErrorServicio {
        Optional<Autor> respuesta = autorRepositorio.findById(id);
        if (respuesta.isPresent()) {

            Autor autor = respuesta.get();
            autor.setAlta(Boolean.TRUE);

            autorRepositorio.save(autor);
        } else {
            throw new ErrorServicio("No se encontro el autor solicitado");
        }
    }

    //METODO PARA LISTAR TODOS LOS AUTORES
    @Transactional(readOnly = true)
    public List<Autor> listarTodos() {
        return autorRepositorio.findAll();
    }
    
    //METODO PARA BUSCAR AUTOR POR ID
//    @Transactional(readOnly = true)
//    public <Optional>Autor buscarPorId(String id){
//        
//        return autorRepositorio.buscarPorId(id);
//    }
    
    @Transactional (readOnly= true)
    public Autor buscarPorId (String id) throws ErrorServicio{
         
    Optional <Autor> optional = autorRepositorio.findById(id);
    
    if(optional.isPresent()){
        
        return optional.get(); // con esto mostramos el autor según el id si esque está presente
    }else {
        throw new ErrorServicio ("El autor con el id ingresado no existe");
    }
         
     }
    
    public Optional<Autor> buscarPorId2(String id) { //metodo para buscar por id
        return autorRepositorio.findById(id);
    }

    //METODO PARA BORRAR UN AUTOR
    @Transactional(propagation = Propagation.NESTED)
    public void borrar(String id) throws ErrorServicio {

        Optional<Autor> respuesta = autorRepositorio.findById(id);
        if (respuesta.isPresent()) {
            
            Autor autor = respuesta.get();
            autorRepositorio.delete(autor);

        } else {
            throw new ErrorServicio("No se encuentra el autor buscado");
        }
    }

    //METODO PARA VALIDAR PARAMETROS
    private void validar(String nombre) throws ErrorServicio {
        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre del autor no puede ser nulo");
        }
    }

}

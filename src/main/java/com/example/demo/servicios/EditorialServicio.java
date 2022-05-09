
package com.example.demo.servicios;

import com.example.demo.entidades.Autor;
import com.example.demo.entidades.Editorial;
import com.example.demo.errores.ErrorServicio;
import com.example.demo.repositorios.EditorialRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EditorialServicio {
    
    @Autowired
    private EditorialRepositorio editorialRepositorio;
    
    //METODO PARA CARGAR UNA EDITORIAL    
    @Transactional(propagation = Propagation.NESTED)
    public void cargar(String nombre) throws ErrorServicio{
        
        validar(nombre);
        
        Editorial editorial = new Editorial();
        
        editorial.setNombre(nombre);
        editorial.setAlta(Boolean.TRUE);
        
        editorialRepositorio.save(editorial);
        
    }
    
    //METODO PARA MODIFICAR UNA EDITORIAL
    @Transactional(propagation = Propagation.NESTED)
    public void modificar(String id, String nombre) throws ErrorServicio{
        
        validar(nombre);
        Optional<Editorial> respuesta = editorialRepositorio.findById(id);
        if(respuesta.isPresent()){
            Editorial editorial = respuesta.get();
            editorial.setNombre(nombre);
            
            editorialRepositorio.save(editorial);
        }else{
            throw new ErrorServicio("No se encontró la editorial solicitada");
        }
    }
    
    //METODO PARA DESHABILITAR UNA EDITORIAL
    @Transactional(propagation = Propagation.NESTED)
    public void deshabilitar(String id) throws ErrorServicio{
        Optional<Editorial> respuesta = editorialRepositorio.findById(id);
        if(respuesta.isPresent()){
            Editorial editorial = respuesta.get();
            editorial.setAlta(Boolean.FALSE);
            
            editorialRepositorio.save(editorial);
        }else{
            throw new ErrorServicio("No se encontró la editorial solicitada");
        }
    }
    
    //METODO PARA REHABILITAR UNA EDITORIAL
    @Transactional(propagation = Propagation.NESTED)
    public void rehabilitar(String id) throws ErrorServicio{
        Optional<Editorial> respuesta = editorialRepositorio.findById(id);
        if(respuesta.isPresent()){
            Editorial editorial = respuesta.get();
            editorial.setAlta(Boolean.TRUE);
            
            editorialRepositorio.save(editorial);
        }else{
            throw new ErrorServicio("No se encontró la editorial solicitada");
        }
    }
    
    //METODO PARA LISTAR TODAS LAS EDITORIALES
    @Transactional(readOnly = true)
    public List<Editorial> mostrarTodas(){
        return editorialRepositorio.findAll();
    }
    
    //METODO PARA BORRAR UNA EDITORIAL
    @Transactional(propagation = Propagation.NESTED)
    public void borrar(String id)throws ErrorServicio{
        
        Optional<Editorial> respuesta = editorialRepositorio.findById(id);
        if(respuesta.isPresent()){
            Editorial editorial = respuesta.get();
            editorialRepositorio.delete(editorial);
            
        }else{
            throw new ErrorServicio("No se encuentra la editorial buscado");
        }        
    }
    
    //METODO BUSCAR POR ID
    @Transactional (readOnly= true)
    public Editorial buscarPorId(String id) throws ErrorServicio{
        Optional <Editorial> optional = editorialRepositorio.findById(id);
    
    if(optional.isPresent()){        
        return optional.get();
    }else {
        throw new ErrorServicio ("El autor con el id ingresado no existe");
    }
    }
    
    public Optional<Editorial> buscarPorId2(String id) { //metodo para buscar por id
        return editorialRepositorio.findById(id);
    }
            
    //METODO PARA VALIDAR PARAMETROS
    private void validar(String nombre) throws ErrorServicio{        
        if(nombre == null || nombre.isEmpty()){
            throw new ErrorServicio("El nombre de la editorial no puede estar vacio");
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.repositorios;

import com.example.demo.entidades.Autor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepositorio extends JpaRepository<Autor, String>  {
    
    //BUSCAR AUTOR POR NOMBRE
    @Query("SELECT a FROM Autor a WHERE a.nombre = :nombre")
    public List<Autor> buscarPorNombre(@Param("nombre")String id);
    
    //BUSCAR AUTOR POR ID
    @Query("SELECT a FROM Autor a WHERE a.id = :id")
    public List<Autor> buscarPorId(@Param("id")String id);
    
    //LISTAR TODOS LOS AUTORES
    @Query ("SELECT a FROM Autor a")
    public List<Autor> listarTodos();
    
    //GUARDAR UN NUEVO AUTOR
    //@Query ("INSERT INTO autor VALUES (:nombre, :alta)")
    //public Autor guardarAutor (@Param("nombre") String nombre, @Param("alta") Boolean alta);// SERVIRA?
    
    //ACTUALIZAR NOMBRE UN AUTOR
    //@Query ("UPDATE autor a SET a.nombre WHERE a.id = id")
    //public Autor actualizarAutor (@Param("nombre") String nombre, @Param("id") String id);
    
    //DAR DE BAJA UN AUTOR
    //@Query ("UPDATE autor a SET a.alta = :alta WHERE a.id = id")
    //public Autor eliminarAutor (@Param("alta") Boolean alta, @Param("id") String id);
}

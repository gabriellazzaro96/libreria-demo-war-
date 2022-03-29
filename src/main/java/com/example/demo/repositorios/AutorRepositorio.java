/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.repositorios;

import com.example.demo.entidades.Autor;
import com.example.demo.entidades.Editorial;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepositorio extends JpaRepository<Autor, String>  {
    
    //BUSCAR AUTOR POR NOMBRE
    @Query ("SELECT a FROM autor a WHERE a.nombre = :nombre")
    public Autor buscarPorNombre(@Param ("nombre")String nombre);
    
    //BUSCAR AUTOR POR ID
    @Query ("SELECT a FROM autor a WHERE a.id = :id")
    public Autor buscarAutorPorId(@Param ("id")String id);
    
    //LISTAR TODOS LOS AUTORES
    @Query ("SELECT * FROM autor")
    public List<Autor> listarTodosAutores();
    
    //GUARDAR UN NUEVO AUTOR
    @Query ("INSERT INTO autor VALUES (:nombre, :alta)")
    public Autor guardarAutor (@Param("nombre") String nombre, @Param("alta") Boolean alta);// SERVIRA?
    
    //ACTUALIZAR NOMBRE UN AUTOR
    @Query ("UPDATE autor a SET a.nombre WHERE a.id = id")
    public Autor actualizarAutor (@Param("nombre") String nombre, @Param("id") String id);
    
    //DAR DE BAJA UN AUTOR
    @Query ("UPDATE autor a SET a.alta = :alta WHERE a.id = id")
    public Autor eliminarAutor (@Param("alta") Boolean alta, @Param("id") String id);
}

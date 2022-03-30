
package com.example.demo.repositorios;

import com.example.demo.entidades.Autor;
import com.example.demo.entidades.Editorial;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EditorialRepositorio extends JpaRepository<Editorial, String> {
    
    //BUSCAR EDITORIAL POR NOMBRE
    @Query ("SELECT e FROM editorial e WHERE e.nombre = :nombre")
    public Editorial buscarPorNombre(@Param ("nombre")String nombre);
    
    //BUSCAR EDITORIAL POR ID
    @Query ("SELECT e FROM editorial e WHERE e.id = :id")
    public Editorial buscarEditorialPorId(@Param ("id")String id);
    
    //LISTAR TODOS LOS AUTORES
    @Query ("SELECT * FROM editorial")
    public List<Editorial> listarTodosEditoriales();
    
    //INSERTAR UNA NUEVA EDITORIAL
    //@Query ("INSERT INTO editorial VALUES (:nombre, :alta)")
    //public Editorial guardarEditorial (@Param("nombre") String nombre, @Param("alta") Boolean alta);// SERVIRA?
    
    //CAMBIAR NOMBRE EDITORIAL
    //@Query ("UPDATE editorial e SET e.nombre WHERE a.id = id")
    //public Editorial actualizarEditorial (@Param("nombre") String nombre, @Param("id") String id);
    
    //DAR DE BAJA UNA NUEVA EDITORIAL
    //@Query ("UPDATE editorial e SET e.alta = :alta WHERE e.id = id")
    //public Editorial eliminarEditorial (@Param("alta") Boolean alta, @Param("id") String id);
    
}

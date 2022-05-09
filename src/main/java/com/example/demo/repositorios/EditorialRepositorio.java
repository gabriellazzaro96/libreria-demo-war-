
package com.example.demo.repositorios;

import com.example.demo.entidades.Editorial;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EditorialRepositorio extends JpaRepository<Editorial, String> {
    
    //BUSCAR EDITORIAL POR NOMBRE
    @Query("SELECT e FROM Editorial e WHERE e.nombre = :nombre")
    public List<Editorial> buscarPorNombre(@Param("nombre")String id);
    
    //BUSCAR EDITORIAL POR ID
    @Query("SELECT e FROM Editorial e WHERE e.id = :id")
    public List<Editorial> buscarEditorialPorId(@Param("id")String id);
    
    //LISTAR TODOS LOS AUTORES
    /*@Query ("SELECT e FROM Editorial e")
    public List<Editorial> listarTodosEditoriales();*/
    
    
}

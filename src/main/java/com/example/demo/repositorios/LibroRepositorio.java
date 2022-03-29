
package com.example.demo.repositorios;

import com.example.demo.entidades.Autor;
import com.example.demo.entidades.Editorial;
import com.example.demo.entidades.Libro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepositorio extends JpaRepository<Libro, String> {
    
    
    //BUSCAR LIBRO POR NOMBRE
    @Query ("SELECT l FROM libro l WHERE l.nombre = :nombre")
    public Libro buscarPorNombre(@Param ("nombre")String nombre);
    
    //BUSCAR LIBRO POR ID
    @Query ("SELECT l FROM libro l WHERE l.id = :id")
    public Libro buscarLibroPorId(@Param ("id")String id);
    
    //AGREGAR UN NUEVO LIBRO
    @Query("INSERT INTO libro VALUES (:isbn, :titulo, :anio, :ejemplares, :ejemplaresPrestados, "
            + ":ejemplaresRestantes, :alta, :autor, :editorial  ")
    public Libro guardarLibro(@Param("isbn") Integer isbn, @Param("titulo") String titulo, @Param("anio") Integer anio, @Param("ejemplares") Integer ejemplares,
            @Param("ejemplaresPrestados") Integer ejemplaresPrestados, @Param("ejemplaresRestantes") Integer ejemplaresRestantes, @Param("alta") Boolean alta,
            @Param("autor") Autor autor, @Param("editorial") Editorial editorial); 

    //LISTAR TODOS LOS LIBROS
    @Query ("SELECT * FROM libro")
    public List<Libro> listarTodosLibros();
    
    //DAR DE BAJA UN LIBRO
    @Query ("UPDATE libro l SET l.alta = :alta WHERE l.id = id")
    public Libro eliminarLibro (@Param("alta") Boolean alta, @Param("id") String id);
    
}
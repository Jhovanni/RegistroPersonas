package com.jhovanni.registropersonas.repositorio;

import com.jhovanni.registropersonas.entidad.Persona;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author jhovanni
 */
public interface PersonaRepository extends JpaRepository<Persona, Integer> {

    Persona findByUsuarioNombre(String nombre);

    /**
     * Busca todos los registro Persona en la BD, obteniendo solo la información
     * requerida para el listarlos.
     *
     * @return
     */
    @Query(value = "SELECT new com.jhovanni.registropersonas.entidad.Persona(p.id,p.nombre,p.edad,p.genero,p.ciudad,p.fotoPerfil.id) FROM Persona p")
    List<Persona> findAllForListing();

    @Query(value = "SELECT p.nombre FROM Persona p WHERE p.usuario.nombre = :nombre")
    String findNombreByUsuarioNombre(@Param("nombre") String nombre);
}

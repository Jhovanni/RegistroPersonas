package com.jhovanni.registropersonas.repositorio;

import com.jhovanni.registropersonas.entidad.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jhovanni
 */
public interface PersonaRepository extends JpaRepository<Persona, Integer> {

    Persona findByUsuarioNombre(String nombre);

}

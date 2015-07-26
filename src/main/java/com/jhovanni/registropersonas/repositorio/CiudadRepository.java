package com.jhovanni.registropersonas.repositorio;

import com.jhovanni.registropersonas.entidad.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jhovanni
 */
public interface CiudadRepository extends JpaRepository<Ciudad, Integer>{
    
}

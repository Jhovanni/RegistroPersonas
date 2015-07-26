package com.jhovanni.registropersonas.repositorio;

import com.jhovanni.registropersonas.entidad.Foto;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jhovanni
 */
public interface FotoRepository extends JpaRepository<Foto, Integer>{
    
}

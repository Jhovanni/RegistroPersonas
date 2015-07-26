package com.jhovanni.registropersonas.repositorio;

import com.jhovanni.registropersonas.entidad.Permiso;
import com.jhovanni.registropersonas.entidad.PermisoId;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jhovanni
 */
public interface PermisoRepository extends JpaRepository<Permiso, PermisoId>{
    
}

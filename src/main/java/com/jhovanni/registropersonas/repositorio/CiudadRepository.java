package com.jhovanni.registropersonas.repositorio;

import com.jhovanni.registropersonas.entidad.Ciudad;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jhovanni
 */
public interface CiudadRepository extends JpaRepository<Ciudad, Integer> {

    List<Ciudad> findAllByOrderByIdAsc();

}

package com.jhovanni.registropersonas.repositorio;

import com.jhovanni.registropersonas.entidad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jhovanni
 */
public interface UsuarioRepository extends JpaRepository<Usuario, String>{

}

package com.jhovanni.registropersonas.hibernate;

import com.jhovanni.registropersonas.entidad.Usuario;
import com.jhovanni.registropersonas.repositorio.UsuarioRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Servicio usado en login del usuario. Su labor es conectarse a la base de
 * datos para obtener el registro del usuario y loguearlo
 *
 * @author jhovanni
 */
@Service
public class LoginService implements UserDetailsService {

    private static final Logger log = LogManager.getLogger();

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Carga un usuario de la base de datos
     *
     * @param nombreUsuario llave primaria del usuario a ser logueado
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
        log.entry(nombreUsuario);
        Usuario usuario = usuarioRepository.findOne(nombreUsuario);
        if (usuario == null) {
            log.debug("Nombre de usuario no encontrado");
            throw new UsernameNotFoundException(nombreUsuario);
        }
        return log.exit(usuario);
    }
}

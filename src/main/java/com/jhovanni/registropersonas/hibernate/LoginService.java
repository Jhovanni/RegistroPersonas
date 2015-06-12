package com.jhovanni.registropersonas.hibernate;

import com.jhovanni.registropersonas.entidad.Usuario;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Servicio usado en login del usuario.
 * Su labor es conectarse a la base de datos para obtener el estado el registro del usuario y loguearlo
 * @author jhovanni
 */
@Service

public class LoginService implements UserDetailsService {

    private static final Logger log = LogManager.getLogger();

    @Autowired
    private Repositorio<Usuario> usuarioRepositorio;

    /**
     * Carga un usuario de la base de datos
     * @param nombreUsuario llave primaria del usuario a ser logueado
     * @return
     * @throws UsernameNotFoundException 
     */
    @Override
    public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
        log.entry(nombreUsuario);
        Usuario usuario = usuarioRepositorio.get(nombreUsuario);
        return log.exit(new User(usuario.getNombre(), usuario.getClave(), usuario.getPermisos()));
    }
}

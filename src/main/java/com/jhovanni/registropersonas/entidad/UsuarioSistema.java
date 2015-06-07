package com.jhovanni.registropersonas.entidad;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author jhovanni
 */
class UsuarioSistema extends User {

    public UsuarioSistema(Usuario usuario) {
        super(usuario.getNombre(), usuario.getClave(), usuario.isActivo(), true, true, true, AuthorityUtils.createAuthorityList(usuario.getPermisos().toString()));
    }
}

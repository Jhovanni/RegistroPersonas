/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhovanni.registropersonas.factory;

import com.jhovanni.registropersonas.entidad.Nivel;
import com.jhovanni.registropersonas.entidad.Permiso;
import com.jhovanni.registropersonas.entidad.Usuario;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class UsuarioFactory {

    private static int count = 1;

    public static Usuario get() {
        Usuario u = new Usuario();
        u.setActivo(true);
        u.setNombre("usuario" + count);
        u.setClave("claveclave");
        count++;
        return u;
    }

    public static Usuario get(Nivel... niveles) {
        Usuario u = get();
        u.setPermisos(new ArrayList<Permiso>(0));
        for (Nivel nivel : niveles) {
            u.getPermisos().add(PermisoFactory.get(u, nivel));
        }
        return u;
    }

}

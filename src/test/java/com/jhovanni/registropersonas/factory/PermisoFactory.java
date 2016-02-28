/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhovanni.registropersonas.factory;

import com.jhovanni.registropersonas.entidad.Nivel;
import com.jhovanni.registropersonas.entidad.Permiso;
import com.jhovanni.registropersonas.entidad.Usuario;

/**
 *
 * @author Administrator
 */
public final class PermisoFactory {
    public static Permiso get(Usuario usuario, Nivel nivel){
        Permiso p = new Permiso();
        p.setUsuario(usuario);
        p.setNivel(nivel);
        return p;
    }
    
}

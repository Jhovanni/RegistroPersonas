/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhovanni.registropersonas.factory;

import com.jhovanni.registropersonas.entidad.Usuario;

/**
 *
 * @author Administrator
 */
public class UsuarioFactory {
    private static int count = 1;
    public static Usuario get(){
        Usuario u = new Usuario();
        u.setActivo(true);
        u.setNombre("usuario"+count);
        u.setClave("claveclave");
        count++;
        return u;
    }
    
}

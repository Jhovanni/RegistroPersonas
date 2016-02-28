/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhovanni.registropersonas.factory;

import com.jhovanni.registropersonas.entidad.Ciudad;

/**
 *
 * @author Administrator
 */
public abstract class CiudadFactory {

    private static int count = 1;

    public static Ciudad get() {
        Ciudad c = new Ciudad();
        c.setId(count);
        c.setNombre("CiudadFactory");
        count++;
        return c;
    }

    public static Ciudad get(String nombre) {
        Ciudad c = get();
        c.setNombre(nombre);
        return c;
    }

}

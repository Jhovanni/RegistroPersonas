/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhovanni.registropersonas.factory;

import com.jhovanni.registropersonas.entidad.Foto;

/**
 *
 * @author Administrator
 */
public class FotoFactory {

    private static int count = 1;

    public static Foto get() {
        Foto foto = new Foto();
        foto.setId(count);
        foto.setNombre("Foto default");
        foto.setContenido(new byte[0]);
        count++;
        return foto;
    }

}

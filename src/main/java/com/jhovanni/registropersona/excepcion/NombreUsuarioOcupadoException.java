/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhovanni.registropersona.excepcion;

/**
 *
 * @author Administrator
 */
public class NombreUsuarioOcupadoException extends Exception {

    public NombreUsuarioOcupadoException(String nombreUsuario) {
        super("Nombre de usuario ya ocupado en el sistema <" + nombreUsuario + ">");
    }

}

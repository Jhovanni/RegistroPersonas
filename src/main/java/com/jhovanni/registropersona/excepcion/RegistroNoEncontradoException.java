/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhovanni.registropersona.excepcion;

import java.io.Serializable;

/**
 *
 * @author Administrator
 */
public class RegistroNoEncontradoException extends Exception {

    public RegistroNoEncontradoException(Serializable registro) {
        super("Registro no encontrado en la base de datos " + registro);
    }

}

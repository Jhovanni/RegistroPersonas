/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhovanni.registropersonas.formato;

import com.jhovanni.registropersonas.entidad.Ciudad;
import com.jhovanni.registropersonas.hibernate.ServicioRegistro;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jhovanni
 */
@Component
public class CiudadFormatter implements Formatter<Ciudad> {

    @Autowired
    private ServicioRegistro servicio;

    @Override
    public String print(Ciudad ciudad, Locale locale) {
        return Integer.toString(ciudad.getId());
    }

    @Override
    public Ciudad parse(String id, Locale locale) throws ParseException {
        return servicio.getCiudad(Integer.parseInt(id));
    }

}

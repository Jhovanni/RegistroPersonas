package com.jhovanni.registropersonas.control;

import com.jhovanni.registropersonas.entidad.Ciudad;
import com.jhovanni.registropersonas.hibernate.Servicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jhovanni
 */
@Component
public class CiudadConverter implements Converter<String, Ciudad> {

    @Autowired
    private Servicio servicio;

    @Override
    public Ciudad convert(String id) {
        return servicio.getCiudad(Integer.parseInt(id));
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhovanni.registropersonas.factory;

import com.jhovanni.registropersonas.entidad.Ciudad;
import com.jhovanni.registropersonas.entidad.Genero;
import com.jhovanni.registropersonas.entidad.Persona;
import com.jhovanni.registropersonas.entidad.Usuario;

/**
 *
 * @author Administrator
 */
public class PersonaFactory {
    private static int count = 1;
    public static Persona get(){
        Persona p = new Persona();
        p.setEdad(27);
        p.setGenero(Genero.M);
        p.setId(count);
        p.setNombre("Persona");
        count++;
        return p;
    }
    public static Persona get(Ciudad ciudad){
        Persona p = get();
        p.setCiudad(ciudad);
        return p;
    }
    
    public static Persona get(Ciudad ciudad, Usuario usuario){
        Persona p = get(ciudad);
        p.setUsuario(usuario);
        return p;
    }
}

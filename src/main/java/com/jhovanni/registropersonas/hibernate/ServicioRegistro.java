package com.jhovanni.registropersonas.hibernate;

import com.jhovanni.registropersonas.entidad.Ciudad;
import com.jhovanni.registropersonas.entidad.Nivel;
import com.jhovanni.registropersonas.entidad.Permiso;
import com.jhovanni.registropersonas.entidad.Persona;
import com.jhovanni.registropersonas.entidad.Usuario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jhovanni
 */
@Service
public class ServicioRegistro implements Servicio {

    @Autowired
    private Repositorio<Ciudad> ciudadRepositorio;
    @Autowired
    private Repositorio<Persona> personaRepositorio;
    @Autowired
    private Repositorio<Usuario> usuarioRepositorio;
    @Autowired
    private Repositorio<Permiso> permisoRepositorio;

    @Override
    public Usuario getUsuario(String nombre) {
        return usuarioRepositorio.get(nombre);
    }

    @Override
    public Ciudad getCiudad(int id) {
        return ciudadRepositorio.get(id);
    }

    @Override
    public List<Ciudad> getCiudades() {
        return ciudadRepositorio.get();
    }

    @Override
    public List<Persona> getPersonas() {
        return personaRepositorio.get();
    }

    @Override
    public int registrarPersona(Persona persona) {
        return (int) personaRepositorio.crear(persona);
    }

    @Override
    public int registrarPersona(Persona persona, String nombreUsuario, String clave) {
        Usuario usuario = new Usuario();
        usuario.setNombre(nombreUsuario);
        usuario.setClave(clave);
        usuario.setActivo(true);
        usuarioRepositorio.crear(usuario);

        Permiso permiso = new Permiso();
        permiso.setUsuario(usuario);
        permiso.setNivel(Nivel.usuario);
        permisoRepositorio.crear(permiso);

        persona.setUsuario(usuario);
        return (int) personaRepositorio.crear(persona);
    }

    @Override
    public Persona getPersona(int id) {
        return personaRepositorio.get(id);
    }

    @Override
    public void borrarPersona(Persona persona) {
        Usuario usuario = persona.getUsuario();
        List<Permiso> permisos = usuario.getPermisos();

        personaRepositorio.borrar(persona);
        for (Permiso permiso : permisos) {
            permisoRepositorio.borrar(permiso);
        }
        usuarioRepositorio.borrar(usuario);
    }

    @Override
    public void borrarPersona(int id) {
        borrarPersona(getPersona(id));
    }
}

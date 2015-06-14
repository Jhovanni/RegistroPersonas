package com.jhovanni.registropersonas.hibernate;

import com.jhovanni.registropersonas.entidad.Ciudad;
import com.jhovanni.registropersonas.entidad.Foto;
import com.jhovanni.registropersonas.entidad.Nivel;
import com.jhovanni.registropersonas.entidad.Permiso;
import com.jhovanni.registropersonas.entidad.Persona;
import com.jhovanni.registropersonas.entidad.Usuario;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jhovanni
 */
@Service
public class ServicioRegistro implements Servicio {
    private static final Logger log = LogManager.getLogger();

    @Autowired
    private Repositorio<Ciudad> ciudadRepositorio;
    @Autowired
    private Repositorio<Persona> personaRepositorio;
    @Autowired
    private Repositorio<Usuario> usuarioRepositorio;
    @Autowired
    private Repositorio<Permiso> permisoRepositorio;
    @Autowired
    private Repositorio<Foto> fotoRepositorio;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Usuario getUsuario(String nombre) {
        log.entry(nombre);
        return log.exit(usuarioRepositorio.get(nombre));
    }

    @Override
    public Ciudad getCiudad(int id) {
        log.entry(id);
        return log.exit(ciudadRepositorio.get(id));
    }

    @Override
    public List<Ciudad> getCiudades() {
        log.entry();
        return log.exit(ciudadRepositorio.get());
    }

    @Override
    public List<Persona> getPersonas() {
        log.entry();
        return log.exit(personaRepositorio.get());
    }

    @Override
    public int registrarPersona(Persona persona) {
        log.entry(persona);
        return log.exit((int) personaRepositorio.crear(persona));
    }

    @Override
    public int registrarPersona(Persona persona, String nombreUsuario, String clave) {
        log.entry(persona, nombreUsuario, clave);
        Usuario usuario = new Usuario();
        usuario.setNombre(nombreUsuario);
        usuario.setClave(passwordEncoder.encode(clave));
        usuario.setActivo(true);
        usuarioRepositorio.crear(usuario);
        
        if (persona.getFoto() != null) {
            log.info("Guardando archivo (foto) seleccionado en base de datos");
            fotoRepositorio.crear(persona.getFoto());
        }

        Permiso permiso = new Permiso();
        permiso.setUsuario(usuario);
        permiso.setNivel(Nivel.usuario);
        permisoRepositorio.crear(permiso);

        persona.setUsuario(usuario);
        return log.exit((int) personaRepositorio.crear(persona));
    }

    @Override
    public Persona getPersona(int id) {
        log.entry(id);
        return log.exit(personaRepositorio.get(id));
    }
    
    @Override
    public Persona getPersona(String nombreUsuario) {
        log.entry(nombreUsuario);
        return log.exit(personaRepositorio.get(nombreUsuario));
    }

    @Override
    public void borrarPersona(Persona persona) {
        log.entry(persona);
        Usuario usuario = persona.getUsuario();
        List<Permiso> permisos = usuario.getPermisos();
        Foto foto = persona.getFoto();

        personaRepositorio.borrar(persona);
        for (Permiso permiso : permisos) {
            permisoRepositorio.borrar(permiso);
        }
        if (foto != null) {
            log.info("Borrando archivo de foto");
            fotoRepositorio.borrar(foto);
        }
        usuarioRepositorio.borrar(usuario);
        log.exit();
    }

    @Override
    public void borrarPersona(int id) {
        log.entry(id);
        borrarPersona(getPersona(id));
        log.exit();
    }

    @Override
    public void editarPersona(Persona persona) {
        log.entry(persona);
        Persona actual = personaRepositorio.get(persona.getId());
        //actualizar los valores que se guardarán únicamente
        actual.setNombre(persona.getNombre());
        actual.setEdad(persona.getEdad());
        actual.setGenero(persona.getGenero());
        actual.setCiudad(persona.getCiudad());
        personaRepositorio.guardar(actual);
        log.exit();
    }

    @Override
    public Foto getFoto(int id) {
        log.entry(id);
        return log.exit(fotoRepositorio.get(id));
    }
}

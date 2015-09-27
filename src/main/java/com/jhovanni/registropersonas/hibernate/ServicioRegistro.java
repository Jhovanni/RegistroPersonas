package com.jhovanni.registropersonas.hibernate;

import com.jhovanni.registropersonas.entidad.Ciudad;
import com.jhovanni.registropersonas.entidad.Foto;
import com.jhovanni.registropersonas.entidad.Nivel;
import com.jhovanni.registropersonas.entidad.Permiso;
import com.jhovanni.registropersonas.entidad.Persona;
import com.jhovanni.registropersonas.entidad.Usuario;
import com.jhovanni.registropersonas.repositorio.CiudadRepository;
import com.jhovanni.registropersonas.repositorio.FotoRepository;
import com.jhovanni.registropersonas.repositorio.PermisoRepository;
import com.jhovanni.registropersonas.repositorio.PersonaRepository;
import com.jhovanni.registropersonas.repositorio.UsuarioRepository;
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
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CiudadRepository ciudadRepository;
    @Autowired
    private PersonaRepository personaRepository;
    @Autowired
    private PermisoRepository permisoRepository;
    @Autowired
    private FotoRepository fotoRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Usuario getUsuario(String nombre) {
        log.entry(nombre);
        return log.exit(usuarioRepository.findOne(nombre));
    }

    @Override
    public Ciudad getCiudad(int id) {
        log.entry(id);
        return log.exit(ciudadRepository.findOne(id));
    }

    @Override
    public List<Ciudad> getCiudades() {
        log.entry();
        return log.exit(ciudadRepository.findAll());
    }

    @Override
    public List<Persona> getPersonas() {
        log.entry();
        return log.exit(personaRepository.findAllForListing());
    }

    @Override
    public Persona registrarPersona(Persona persona, String nombreUsuario, String clave) {
        log.entry(persona, nombreUsuario, clave);
        Usuario usuario = new Usuario();
        usuario.setNombre(nombreUsuario);
        usuario.setClave(passwordEncoder.encode(clave));
        usuario.setActivo(true);
        usuario = usuarioRepository.save(usuario);

        if (persona.getFoto() != null) {
            log.info("Guardando archivo (foto) seleccionado en base de datos");
            fotoRepository.save(persona.getFoto());
        }

        Permiso permiso = new Permiso();
        permiso.setUsuario(usuario);
        permiso.setNivel(Nivel.usuario);
        permisoRepository.save(permiso);

        persona.setUsuario(usuario);
        return log.exit(personaRepository.saveAndFlush(persona));
    }

    @Override
    public Persona getPersona(int id) {
        log.entry(id);
        return log.exit(personaRepository.findOne(id));
    }

    @Override
    public Persona getPersona(String nombreUsuario) {
        log.entry(nombreUsuario);
        return log.exit(personaRepository.findByUsuarioNombre(nombreUsuario));
    }

    @Override
    public void borrarPersona(Persona persona) {
        log.entry(persona);
        Usuario usuario = persona.getUsuario();
        List<Permiso> permisos = usuario.getPermisos();
        Foto foto = persona.getFoto();

        personaRepository.delete(persona);
        if (permisos != null) {
            for (Permiso permiso : permisos) {
                permisoRepository.delete(permiso);
            }
        }
        if (foto != null) {
            log.info("Borrando archivo de foto");
            fotoRepository.delete(foto);
        }
        usuarioRepository.delete(usuario);
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
        Persona actual = personaRepository.findOne(persona.getId());
        //actualizar los valores que se guardarán únicamente
        actual.setNombre(persona.getNombre());
        actual.setEdad(persona.getEdad());
        actual.setGenero(persona.getGenero());
        actual.setCiudad(persona.getCiudad());
        personaRepository.saveAndFlush(actual);
        log.exit();
    }

    @Override
    public Foto getFoto(int id) {
        log.entry(id);
        return log.exit(fotoRepository.findOne(id));
    }
}

package com.jhovanni.registropersonas.hibernate;

import com.jhovanni.registropersona.excepcion.NombreUsuarioOcupadoException;
import com.jhovanni.registropersona.excepcion.RegistroNoEncontradoException;
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
public class ServicioRegistro {

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

    /**
     * Obtiene un usuario en base a su nombre
     *
     * @param nombre
     * @return
     */
    public Usuario getUsuario(String nombre) {
        log.entry(nombre);
        return log.exit(usuarioRepository.findOne(nombre));
    }

    /**
     * Obtiene una ciudad en base a su id
     *
     * @param id
     * @return
     */
    public Ciudad getCiudad(int id) {
        log.entry(id);
        return log.exit(ciudadRepository.findOne(id));
    }

    /**
     * Obtiene todas las ciudades encontradas en el sistema
     *
     * @return
     */
    public List<Ciudad> getCiudades() {
        log.entry();
        return log.exit(ciudadRepository.findAllByOrderByIdAsc());
    }

    /**
     * Obtiene todas las personas encontradas en el sistema
     *
     * @return
     */
    public List<Persona> getPersonas() {
        log.entry();
        return log.exit(personaRepository.findAllForListing());
    }

    /**
     * Crea un nuevo registro persona en el sistema. Al crearse la persona, se
     * crean a la vez el registro usuario con permisos por defecto
     *
     * @param persona
     * @param nombreUsuario
     * @param clave
     * @return
     * @throws
     * com.jhovanni.registropersona.excepcion.NombreUsuarioOcupadoException si
     * el nombre de usuario seleccionado ya se encuentra en uso
     */
    public Persona registrarPersona(Persona persona, String nombreUsuario, String clave) throws NombreUsuarioOcupadoException {
        log.entry(persona, nombreUsuario, clave);

        if (usuarioRepository.exists(nombreUsuario)) {
            throw new NombreUsuarioOcupadoException(nombreUsuario);
        }
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
        permiso.setNivel(Nivel.Usuario);
        permisoRepository.save(permiso);

        persona.setUsuario(usuario);
        return log.exit(personaRepository.saveAndFlush(persona));
    }

    /**
     * Obtiene una persona en base a su id
     *
     * @param id
     * @return
     */
    public Persona getPersona(int id) {
        log.entry(id);
        return log.exit(personaRepository.findOne(id));
    }

    /**
     * Obtiene una persona en base al nombre de la instancia usuario asociada
     *
     * @param nombreUsuario
     * @return
     */
    public Persona getPersona(String nombreUsuario) {
        log.entry(nombreUsuario);
        return log.exit(personaRepository.findByUsuarioNombre(nombreUsuario));
    }

    /**
     * Recibe una persona y la borra del sistema
     *
     * @param persona
     * @throws RegistroNoEncontradoException en caso de que la persona recibida
     * no exista en el sistema
     */
    public void borrarPersona(Persona persona) throws RegistroNoEncontradoException {
        log.entry(persona);
        if (!personaRepository.exists(persona.getId())) {
            throw new RegistroNoEncontradoException(persona);
        }
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

    /**
     * Recibe el id de una persona y la borra del sistema
     *
     * @param id
     * @throws RegistroNoEncontradoException en caso de que no exista una
     * persona que corresponda con el id recibido
     */
    public void borrarPersona(int id) throws RegistroNoEncontradoException {
        log.entry(id);
        borrarPersona(getPersona(id));
        log.exit();
    }

    /**
     * Recibe una persona y actualiza sus campos nombre, edad, genero y ciudad
     * en la base de datos
     *
     * @param persona
     * @return
     * @throws RegistroNoEncontradoException en caso de que la persona recibida
     * no exista ya en el sistema
     */
    public Persona editarPersona(Persona persona) throws RegistroNoEncontradoException {
        log.entry(persona);

        Persona actual = personaRepository.findOne(persona.getId());
        if (actual == null) {
            throw new RegistroNoEncontradoException(persona);
        }
        //actualizar los valores que se guardarán únicamente
        actual.setNombre(persona.getNombre());
        actual.setEdad(persona.getEdad());
        actual.setGenero(persona.getGenero());
        actual.setCiudad(persona.getCiudad());
        return log.exit(personaRepository.saveAndFlush(actual));
    }

    /**
     * Obtiene una foto en base a su id
     *
     * @param id
     * @return
     */
    public Foto getFoto(int id) {
        log.entry(id);
        return log.exit(fotoRepository.findOne(id));
    }
}

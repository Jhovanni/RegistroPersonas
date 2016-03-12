/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhovanni.registropersonas.hibernate;

import com.jhovanni.registropersona.excepcion.NombreUsuarioOcupadoException;
import com.jhovanni.registropersona.excepcion.RegistroNoEncontradoException;
import com.jhovanni.registropersonas.entidad.Foto;
import com.jhovanni.registropersonas.entidad.Permiso;
import com.jhovanni.registropersonas.entidad.Persona;
import com.jhovanni.registropersonas.entidad.Usuario;
import com.jhovanni.registropersonas.factory.CiudadFactory;
import com.jhovanni.registropersonas.factory.FotoFactory;
import com.jhovanni.registropersonas.factory.PersonaFactory;
import com.jhovanni.registropersonas.factory.UsuarioFactory;
import com.jhovanni.registropersonas.repositorio.CiudadRepository;
import com.jhovanni.registropersonas.repositorio.FotoRepository;
import com.jhovanni.registropersonas.repositorio.PermisoRepository;
import com.jhovanni.registropersonas.repositorio.PersonaRepository;
import com.jhovanni.registropersonas.repositorio.UsuarioRepository;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author Administrator
 */
@RunWith(MockitoJUnitRunner.class)
public class ServicioRegistroTest extends TestCase {

    @InjectMocks
    private ServicioRegistro servicio;
    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private CiudadRepository ciudadRepository;
    @Mock
    private PersonaRepository personaRepository;
    @Mock
    private PermisoRepository permisoRepository;
    @Mock
    private FotoRepository fotoRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    private Persona persona;
    private List<Persona> personas;

    /**
     * Prueba buscar el id de una persona en base al nombre de usuario. Se
     * verifica que la llamada al método de búsqueda se realize propiamente
     */
    @Test
    public void testGetPersonaIdByUsuarioNombre_nombreRecibido_busquedaPorNombreRealizada() {
        String nombreUsuario = "Usuario test";
        servicio.getPersonaId(nombreUsuario);
        Mockito.verify(personaRepository, Mockito.times(1)).findIdByUsuarioNombre(nombreUsuario);

    }

    /**
     * Probar editar persona cuando la variable fotoPerfil no es nula. En tal
     * caso se espera que se cree un nuevo registro para la foto recibida, el
     * cual se asigna como fotoPerfil de la persona, reemplazando la existente.
     * Ninguna foto se borra al realizar tal operación
     *
     * @throws RegistroNoEncontradoException
     */
    @Test
    public void testEditarPersona_fotoModificada_registroFotoActualizado() throws RegistroNoEncontradoException {
        persona.setFotoPerfil(FotoFactory.get());
        Mockito.when(fotoRepository.save(Mockito.isA(Foto.class))).thenReturn(persona.getFotoPerfil());

        servicio.editarPersona(persona);

        //la foto debe de tener definido a que persona pertenece
        assertNotNull(persona.getFotoPerfil().getPersona());
        Mockito.verify(personaRepository, Mockito.times(1)).findOne(persona.getId());
        //se espera la actualización del registro persona
        Mockito.verify(personaRepository, Mockito.times(1)).saveAndFlush(Mockito.isA(Persona.class));
        //se espera que sea creado el nuevo registro foto reemplazando el anterior si existe
        Mockito.verify(fotoRepository, Mockito.times(1)).save(persona.getFotoPerfil());
        Mockito.verifyNoMoreInteractions(fotoRepository, personaRepository);
    }

    /**
     * Prueba registrar persona cuando los datos recibidos son admitidos. Ésta
     * operación debe de crear registros nuevos para usuario, permiso y persona
     *
     * @throws NombreUsuarioOcupadoException
     */
    @Test
    public void testRegistrarPersona_informacionCorecta_creadosUsuarioPermisoYPersona() throws NombreUsuarioOcupadoException {
        servicio.registrarPersona(persona, persona.getUsuario().getNombre(), persona.getUsuario().getClave());
        Mockito.verify(usuarioRepository, Mockito.times(1)).save(Mockito.isA(Usuario.class));
        Mockito.verify(permisoRepository, Mockito.times(1)).save(Mockito.isA(Permiso.class));
        Mockito.verify(personaRepository, Mockito.times(1)).saveAndFlush(Mockito.isA(Persona.class));
    }

    /**
     * Prueba registrar persona cuando la variable fotoPerfil no es nula. Se
     * verifica que durante el registro de persona, sea tambien creado un
     * registro para la foto y asignada como fotoPerfil de la nueva persona
     *
     * @throws NombreUsuarioOcupadoException
     */
    @Test
    public void testRegistrarPersona_personaConFoto_registroFotoCreado() throws NombreUsuarioOcupadoException {
        persona.setFotoPerfil(FotoFactory.get());
        Mockito.when(fotoRepository.save(persona.getFotoPerfil())).thenReturn(persona.getFotoPerfil());
        servicio.registrarPersona(persona, persona.getUsuario().getNombre(), persona.getUsuario().getClave());
        Mockito.verify(fotoRepository, Mockito.times(1)).save(Mockito.isA(Foto.class));
        assertNotNull(persona.getFotoPerfil().getPersona());
    }

    /**
     * Probar registrar persona cuando ya existe un usuario con el nombre que se
     * ha escogido. En tal escenario, el sistema ha de soltar una excepción
     *
     * @throws NombreUsuarioOcupadoException
     */
    @Test(expected = NombreUsuarioOcupadoException.class)
    public void testRegistrarPersona_nombreUsuarioOcupado_registroYaExistenteExceptionArrojada() throws NombreUsuarioOcupadoException {
        Mockito.when(usuarioRepository.exists(Mockito.isA(String.class))).thenReturn(Boolean.TRUE);
        servicio.registrarPersona(persona, persona.getUsuario().getNombre(), persona.getUsuario().getClave());
    }

    /**
     * Prueba borrar una persona pasando el id de la misma. El sistema buscará
     * la persona por el id, y la borrará junto con sus variables asociadas de
     * la base de datos.
     *
     * @throws RegistroNoEncontradoException
     */
    @Test
    public void testBorrarPersona_pasandoPersonaId_registroBuscadoYLuegoBorrado() throws RegistroNoEncontradoException {
        persona.setFotos(new ArrayList<Foto>(0));
        persona.getFotos().add(FotoFactory.get());
        persona.getFotos().add(FotoFactory.get());
        servicio.borrarPersona(persona.getId());

        //ha de borrarse el registro persona
        Mockito.verify(personaRepository, Mockito.times(1)).findOne(persona.getId());
        //la fotografías asociadas deben ser borradas
        Mockito.verify(fotoRepository, Mockito.times(1)).delete(Matchers.anyListOf(Foto.class));
        //ha de borrarse el registro persona
        Mockito.verify(personaRepository, Mockito.times(1)).delete(Mockito.isA(Persona.class));
        //los permisos asociados deben de ser borrados
        if (persona.getUsuario().getPermisos() != null && !persona.getUsuario().getPermisos().isEmpty()) {
            Mockito.verify(permisoRepository, Mockito.atLeastOnce()).delete(Mockito.isA(Permiso.class));
        }
        //se elimina el registro usuario
        Mockito.verify(usuarioRepository, Mockito.times(1)).delete(Mockito.isA(Usuario.class));
    }

    /**
     * Prueba intentar borrar una persona cuando no existe un registro con el id
     * especificado. Se espera el sistema arroje una excepción acorde al
     * problema.
     *
     * @throws RegistroNoEncontradoException
     */
    @Test(expected = RegistroNoEncontradoException.class)
    public void testBorrarPersona_personaInexistente_registroNoEncontradoExceptionArrojada() throws RegistroNoEncontradoException {
        Mockito.when(personaRepository.findOne(persona.getId())).thenReturn(null);
        servicio.borrarPersona(persona.getId());
    }

    /**
     * Prueba editar un registro persona. Se espera que se realice una búsqueda
     * del registro, y luego se actualize con los datos de la información nueva.
     *
     * @throws RegistroNoEncontradoException
     */
    @Test
    public void testEditarPersona_personaExistente_actualizacionRealizada() throws RegistroNoEncontradoException {
        servicio.editarPersona(persona);

        Mockito.verify(personaRepository, Mockito.times(1)).findOne(persona.getId());
        Mockito.verify(personaRepository, Mockito.times(1)).saveAndFlush(Mockito.isA(Persona.class));
    }

    /**
     * Prueba editar persona cuando no existe un registro cuyo id corresponda
     * con el recibido en el objeto parámetros. El sistema buscará el registro
     * en la base de datos, y al no encontrarlo, se espera lance una excepción
     * tipo {@link RegistroNoEncontradoException}
     *
     * @throws RegistroNoEncontradoException
     */
    @Test(expected = RegistroNoEncontradoException.class)
    public void testEditarPersona_personaInexistente_registroNoEncontradoExceptionArrojada() throws RegistroNoEncontradoException {
        Mockito.when(personaRepository.findOne(persona.getId())).thenReturn(null);

        servicio.editarPersona(persona);
    }

    @Before
    public void before() {
        persona = PersonaFactory.get(CiudadFactory.get(), UsuarioFactory.get());
        personas = new ArrayList<>(0);
        personas.add(persona);

        Mockito.when(personaRepository.findOne(persona.getId())).thenReturn(persona);
        Mockito.when(personaRepository.saveAndFlush(Mockito.isA(Persona.class))).thenReturn(persona);
        Mockito.when(usuarioRepository.exists(Mockito.isA(String.class))).thenReturn(Boolean.FALSE);
        Mockito.when(personaRepository.exists(persona.getId())).thenReturn(Boolean.TRUE);
    }

}

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

    @Test
    public void testRegistrarPersona_informacionCorecta_creadosUsuarioPermisoYPersona() throws NombreUsuarioOcupadoException {
        servicio.registrarPersona(persona, persona.getUsuario().getNombre(), persona.getUsuario().getClave());
        Mockito.verify(usuarioRepository, Mockito.times(1)).save(Mockito.isA(Usuario.class));
        Mockito.verify(permisoRepository, Mockito.times(1)).save(Mockito.isA(Permiso.class));
        Mockito.verify(personaRepository, Mockito.times(1)).saveAndFlush(Mockito.isA(Persona.class));
    }

    @Test
    public void testRegistrarPersona_personaConFoto_registroFotoCreado() throws NombreUsuarioOcupadoException {
        persona.setFoto(FotoFactory.get());
        servicio.registrarPersona(persona, persona.getUsuario().getNombre(), persona.getUsuario().getClave());
        Mockito.verify(fotoRepository, Mockito.times(1)).save(Mockito.isA(Foto.class));
    }

    @Test(expected = NombreUsuarioOcupadoException.class)
    public void testRegistrarPersona_nombreUsuarioOcupado_registroYaExistenteExceptionArrojada() throws NombreUsuarioOcupadoException {
        Mockito.when(usuarioRepository.exists(Mockito.isA(String.class))).thenReturn(Boolean.TRUE);
        servicio.registrarPersona(persona, persona.getUsuario().getNombre(), persona.getUsuario().getClave());
    }

    @Test
    public void testBorrarPersona_pasandoRegistroPersona_personaYObjetosAsociadosBorrados() throws RegistroNoEncontradoException {
        servicio.borrarPersona(persona);

        Mockito.verify(personaRepository, Mockito.times(1)).delete(Mockito.isA(Persona.class));
        if (persona.getUsuario().getPermisos() != null && !persona.getUsuario().getPermisos().isEmpty()) {
            Mockito.verify(permisoRepository, Mockito.atLeastOnce()).delete(Mockito.isA(Permiso.class));
        }
        if (persona.getFoto() != null) {
            Mockito.verify(fotoRepository, Mockito.times(1)).delete(Mockito.isA(Foto.class));
        }
        Mockito.verify(usuarioRepository, Mockito.times(1)).delete(Mockito.isA(Usuario.class));
    }

    @Test
    public void testBorrarPersona_pasandoPersonaId_registroBuscadoYLuegoBorrado() throws RegistroNoEncontradoException {
        servicio.borrarPersona(persona.getId());

        Mockito.verify(personaRepository, Mockito.times(1)).findOne(persona.getId());

        Mockito.verify(personaRepository, Mockito.times(1)).delete(Mockito.isA(Persona.class));
        if (persona.getUsuario().getPermisos() != null && !persona.getUsuario().getPermisos().isEmpty()) {
            Mockito.verify(permisoRepository, Mockito.atLeastOnce()).delete(Mockito.isA(Permiso.class));
        }
        if (persona.getFoto() != null) {
            Mockito.verify(fotoRepository, Mockito.times(1)).delete(Mockito.isA(Foto.class));
        }
        Mockito.verify(usuarioRepository, Mockito.times(1)).delete(Mockito.isA(Usuario.class));
    }

    @Test(expected = RegistroNoEncontradoException.class)
    public void testBorrarPersona_personaInexistente_registroNoEncontradoExceptionArrojada() throws RegistroNoEncontradoException {
        Mockito.when(personaRepository.exists(persona.getId())).thenReturn(Boolean.FALSE);
        servicio.borrarPersona(persona);
    }

    @Test
    public void testEditarPersona_personaExistente_actualizacionRealizada() throws RegistroNoEncontradoException {
        servicio.editarPersona(persona);

        Mockito.verify(personaRepository, Mockito.times(1)).findOne(persona.getId());
        Mockito.verify(personaRepository, Mockito.times(1)).saveAndFlush(Mockito.isA(Persona.class));
    }
    
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

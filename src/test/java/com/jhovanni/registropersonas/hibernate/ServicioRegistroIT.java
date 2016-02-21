package com.jhovanni.registropersonas.hibernate;

import com.jhovanni.registropersonas.config.RootConfigTest;
import com.jhovanni.registropersonas.entidad.Ciudad;
import com.jhovanni.registropersonas.entidad.Foto;
import com.jhovanni.registropersonas.entidad.Genero;
import com.jhovanni.registropersonas.entidad.Persona;
import com.jhovanni.registropersonas.entidad.Usuario;
import java.util.List;
import junit.framework.TestCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jhovanni
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfigTest.class)
@Ignore
public class ServicioRegistroIT extends TestCase {

    private final Logger log = LogManager.getLogger();

    @Autowired
    private ServicioRegistro servicioRegistro;

    @Test
    public void testDepencencias() {
        assertNotNull(servicioRegistro);
    }

    @Test
    public void testGetUsuario() {
        String nombreUsuario = "jhovanni";
        Usuario usuario = servicioRegistro.getUsuario(nombreUsuario);
        assertNotNull(usuario);
        log.info(usuario);
    }

    @Test
    public void testGetCiudad() {
        Ciudad ciudad = servicioRegistro.getCiudad(1);
        assertNotNull(ciudad);
        log.info(ciudad);
    }

    @Test
    public void testGetCiudades() {
        List<Ciudad> ciudades = servicioRegistro.getCiudades();
        for (Ciudad ciudad : ciudades) {
            log.info(ciudad);
        }
    }

    @Test
    public void testGetPersonas() {
        List<Persona> personas = servicioRegistro.getPersonas();
        for (Persona persona : personas) {
            log.info(persona);
        }
    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void testMetodosCRUDParaPersona() {
        int ciudadId = 1;
        Ciudad ciudad = servicioRegistro.getCiudad(ciudadId);
        assertNotNull(ciudad);

        String nombreUsuario = "usuarioTest";
        String clave = "claveTest";

        Persona persona = new Persona();
        persona.setEdad(20);
        persona.setGenero(Genero.M);
        persona.setNombre("TestUser");
        persona.setCiudad(ciudad);
        persona = servicioRegistro.registrarPersona(persona, nombreUsuario, clave);
        log.info(persona);

        int personaId = persona.getId();
        nombreUsuario = persona.getUsuario().getNombre();

        persona = servicioRegistro.getPersona(personaId);
        assertNotNull(persona);
        log.info("Get persona usando ID found " + persona);

        log.debug("Editando nombre de persona");
        persona.setNombre("Usuario test editado");

        persona = servicioRegistro.getPersona(nombreUsuario);
        assertNotNull(persona);
        log.info("Get persona usando nombre de usuario found " + persona);

        log.debug("Borrando persona");
        servicioRegistro.borrarPersona(persona);
        persona = servicioRegistro.getPersona(personaId);
        assertNull(persona);
        log.info("Persona borrada");
    }

    @Test
    public void testgetFoto() {
        String nombreUsuario = "jhovanni";
        Persona persona = servicioRegistro.getPersona(nombreUsuario);
        Foto foto = servicioRegistro.getFoto(persona.getFoto().getId());
        assertNotNull(foto);
    }

}

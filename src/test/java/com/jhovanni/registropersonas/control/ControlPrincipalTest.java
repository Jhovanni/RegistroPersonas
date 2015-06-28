package com.jhovanni.registropersonas.control;

import com.jhovanni.registropersonas.config.DispatcherConfig;
import com.jhovanni.registropersonas.config.RootConfig;
import com.jhovanni.registropersonas.entidad.Ciudad;
import com.jhovanni.registropersonas.entidad.Genero;
import com.jhovanni.registropersonas.entidad.Persona;
import com.jhovanni.registropersonas.hibernate.Servicio;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.MediaType;
import junit.framework.TestCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.*;
import org.junit.Ignore;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Pruebas para el controlador de vistas de la aplicación
 *
 * @author jhovanni
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class, DispatcherConfig.class})
@WebAppConfiguration
@Ignore
public class ControlPrincipalTest extends TestCase{

    private static final Logger log = LogManager.getLogger();
    private MockMvc mvc;

    @InjectMocks
    private ControlPrincipal control;
    @Mock
    private Servicio servicio;

    @Before
    @Override
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(control).build();
    }

    /**
     * Test inyección de dependencias correctas Se asegura que tanto el
     * controlador como el servicio no sean nulos
     */
    @Ignore @Test
    public void testDependencias() {
        log.entry();
        assertNotNull(control);
        assertNotNull(servicio);
        log.exit();
    }

    /**
     * Prueba la navegación a la página de inicio
     *
     * @throws java.lang.Exception
     */
    @Ignore @Test
    public void testInicio() throws Exception {
        log.entry();
        mvc.perform(get("/")).andExpect(status().isOk())
                .andExpect(view().name("inicio"))
                .andExpect(forwardedUrl("inicio"));
        log.exit();
    }

    /**
     * Probar la navegación a la página de listar personas
     *
     * @throws java.lang.Exception
     */
    @Ignore @Test
    public void testListarPersonas() throws Exception {
        log.entry();
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona("jhovanni", 27, Genero.M));
        when(servicio.getPersonas()).thenReturn(personas);

        mvc.perform(get("/persona/lista")).andExpect(status().isOk())
                .andExpect(view().name("persona/lista"))
                .andExpect(forwardedUrl("persona/lista"))
                .andExpect(model().attributeExists("personas"))
                .andExpect(model().attribute("personas", hasSize(1)))
                .andExpect(model().attribute("personas", hasItem(
                                        allOf(
                                                hasProperty("nombre", is("jhovanni")),
                                                hasProperty("edad", is(27)),
                                                hasProperty("genero", is(Genero.M))
                                        )
                                )));
        verify(servicio).getCiudades();
        verify(servicio, times(1)).getPersonas();
        verifyNoMoreInteractions(servicio);
        log.exit();
    }
    
    @Ignore @Test
    public void testPrepararRegistrar() throws Exception {
        log.entry();
        mvc.perform(get("/persona/registrar"))
                .andExpect(status().isOk())
                .andExpect(view().name("persona/registrar"))
                .andExpect(forwardedUrl("persona/registrar"))
                .andExpect(model().attributeExists("personaForm"))
                .andExpect(model().attribute("personaForm", Matchers.isA(PersonaForm.class)));
        log.exit();
    }
    
    @Test
    public void testRegistrar()throws Exception{
        log.entry();
        PersonaForm personaForm = new PersonaForm();
        personaForm.setCiudad(new Ciudad());
        personaForm.setClave("clave");
        personaForm.setClave2("clave");
        personaForm.setEdad(20);
        personaForm.setGenero(Genero.M);
        personaForm.setNombre("Persona");
        personaForm.setNombreUsuario("usuario");
        
        
        mvc.perform(post("/persona/registrar")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .sessionAttr("personaForm", personaForm))
                .andExpect(status().isOk())
                .andExpect(view().name("persona/registrar"))
                .andExpect(forwardedUrl("persona/registrar"));
        log.exit();
    }

}

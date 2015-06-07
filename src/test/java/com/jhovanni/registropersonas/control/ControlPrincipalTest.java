package com.jhovanni.registropersonas.control;

import com.jhovanni.registropersonas.config.DispatcherConfig;
import com.jhovanni.registropersonas.config.Inicializador;
import com.jhovanni.registropersonas.config.RootConfig;
import com.jhovanni.registropersonas.entidad.Ciudad;
import com.jhovanni.registropersonas.entidad.Genero;
import com.jhovanni.registropersonas.entidad.Persona;
import com.jhovanni.registropersonas.hibernate.Servicio;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

/**
 * Pruebas para el controlador de vistas de la aplicación
 *
 * @author jhovanni
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class, DispatcherConfig.class})
@WebAppConfiguration
public class ControlPrincipalTest {

    private static final Logger log = LogManager.getLogger();
    private MockMvc mvc;

    @InjectMocks
    private ControlPrincipal control;
    @Mock
    private Servicio servicio;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(control).build();
    }

    /**
     * Test inyección de dependencias correctas Se asegura que tanto el
     * controlador como el servicio no sean nulos
     */
    @Test
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
    @Test
    public void testInicio() throws Exception {
        log.entry();
        mvc.perform(get("/")).andExpect(status().isFound())
                .andExpect(view().name("redirect:/persona/lista"))
                .andExpect(forwardedUrl(null));
        log.exit();
    }

    /**
     * Probar la navegación a la página de listar personas
     *
     * @throws java.lang.Exception
     */
    @Test
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

}

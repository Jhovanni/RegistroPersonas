package com.jhovanni.registropersonas.control;

import com.jhovanni.registropersonas.excepcion.NombreUsuarioOcupadoException;
import com.jhovanni.registropersonas.excepcion.RegistroNoEncontradoException;
import com.jhovanni.registropersonas.entidad.Nivel;
import com.jhovanni.registropersonas.entidad.Persona;
import com.jhovanni.registropersonas.entidad.Usuario;
import com.jhovanni.registropersonas.factory.CiudadFactory;
import com.jhovanni.registropersonas.factory.PersonaFactory;
import com.jhovanni.registropersonas.factory.UsuarioFactory;
import com.jhovanni.registropersonas.formato.PersonaForm;
import com.jhovanni.registropersonas.hibernate.ServicioRegistro;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.util.NestedServletException;

/**
 * Pruebas para el controlador de vistas de la aplicación.
 *
 * @author jhovanni
 */
@RunWith(MockitoJUnitRunner.class)
public class ControlPrincipalTest extends TestCase {

    private MockMvc mvc;

    @InjectMocks
    private ControlPrincipal control;
    @Mock
    private ServicioRegistro servicio;

    private Persona persona;
    private List<Persona> personas;

    @Before
    @Override
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(control).setViewResolvers(viewResolver()).build();

        persona = PersonaFactory.get(CiudadFactory.get(), UsuarioFactory.get(Nivel.Administrador));
        personas = new ArrayList<>(0);
        personas.add(persona);

        TestingAuthenticationToken token = new TestingAuthenticationToken(persona.getUsuario(), null, new ArrayList<>(persona.getUsuario().getAuthorities()));
        SecurityContextHolder.getContext().setAuthentication(token);

        when(servicio.getPersonas()).thenReturn(personas);
        when(servicio.getPersona(persona.getId())).thenReturn(persona);
        when(servicio.getPersona(persona.getUsuario().getNombre())).thenReturn(persona);
        when(servicio.getPersonaId(persona.getUsuario().getNombre())).thenReturn(persona.getId());
    }

    /**
     * Prueba la petición de login.
     *
     * @throws Exception
     */
    @Test
    public void testPrepararLogin() throws Exception {
        mvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    /**
     * Probar editar una persona, cuando el usuario logueado no posee los roles
     * necesarios, ni tampoco está asociado al registro persona que desea
     * editar. En tal situación se espera, sea arrojada una
     * AccessDeniedException.<br>
     * <small>Parace haber un problema con el runner, porque encierra la
     * excepción, por lo cual he tenido que poner NestedServletException como
     * esperada (esta medida es sólo requerida en el test). TODO: corregir
     * eso</small>
     *
     * @throws Exception
     */
    @Test(expected = NestedServletException.class)
    public void testEditar_usuarioSinRolesYSinRegistroPersona_accesoDenegado() throws Exception {
        Usuario usuario = UsuarioFactory.get(Nivel.Usuario);
        TestingAuthenticationToken token = new TestingAuthenticationToken(usuario, null, new ArrayList<>(usuario.getAuthorities()));
        SecurityContextHolder.getContext().setAuthentication(token);
        PersonaForm personaForm = new PersonaForm(persona);

        mvc.perform(post("/persona/editar/" + persona.getId())
                .sessionAttr("personaForm", personaForm));
    }

    /**
     * Probar editar una persona, cuando el usuario logueado es anonimo. En tal
     * situación se espera, sea arrojada una AccessDeniedException.<br>
     *
     * @throws Exception
     */
    @Test(expected = NestedServletException.class)
    public void testEditar_usuarioAnomino_accesoDenegado() throws Exception {
        AnonymousAuthenticationToken token = new AnonymousAuthenticationToken("invitado", persona.getUsuario().getNombre(), new ArrayList<>(persona.getUsuario().getAuthorities()
        ));
        SecurityContextHolder.getContext().setAuthentication(token);
        PersonaForm personaForm = new PersonaForm(persona);

        mvc.perform(post("/persona/editar/" + persona.getId())
                .sessionAttr("personaForm", personaForm));
    }

    /**
     * Test petición para comprobar si un nombre de usuario ya se encuentra en
     * uso. Verifica que el tipo de contenido regresado sea JSON y que sea
     * compatible con un objeto Boolean. Además verifica que se haga una llamada
     * a
     *
     * @throws Exception
     */
    @Test
    public void testIsNombreUsuarioOcupado() throws Exception {
        String nombreUsuario = "admin";
        mvc.perform(get("/persona/isNombreUsuarioOcupado/" + nombreUsuario))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(anyOf(is(Boolean.TRUE.toString()), is(Boolean.FALSE.toString()))));

        verify(servicio, times(1)).isNombreUsuarioOcupado(nombreUsuario);
    }

    /**
     * Prueba la navegación a la página de inicio
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testInicio() throws Exception {
        mvc.perform(get("/")).andExpect(status().isOk())
                .andExpect(view().name("inicio"));
    }

    /**
     * Probar la navegación a la página de listar personas
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testListarPersonas() throws Exception {

        mvc.perform(get("/persona/lista")).andExpect(status().isOk())
                .andExpect(view().name("persona/lista"))
                .andExpect(model().attributeExists("personas"))
                .andExpect(model().attribute("personas", hasSize(1)))
                .andExpect(model().attribute("personas", Matchers.isA(List.class)
                ));
        verify(servicio).getCiudades();
        verify(servicio, times(1)).getPersonas();
        verifyNoMoreInteractions(servicio);
    }

    /**
     * Probar la petición para registrar nueva persona
     *
     * @throws Exception
     */
    @Test
    public void testPrepararRegistrar() throws Exception {
        mvc.perform(get("/persona/registrar"))
                .andExpect(status().isOk())
                .andExpect(view().name("persona/registrar"))
                .andExpect(model().attributeExists("personaForm"))
                .andExpect(model().attribute("personaForm", Matchers.isA(PersonaForm.class)));
    }

    /**
     * Prueba registrar persona cuando el formulario de registro es válido
     *
     * @throws Exception
     */
    @Test
    public void testRegistrar_personaValida_metodoRegistrarPersonaLlamado() throws Exception {
        PersonaForm personaForm = new PersonaForm(PersonaFactory.get(CiudadFactory.get(), UsuarioFactory.get()));

        mvc.perform(post("/persona/registrar")
                .sessionAttr("personaForm", personaForm))
                .andExpect(status().isOk())
                .andExpect(view().name("persona/registrar"))
                .andExpect(model().attributeExists("usuarioRegistrado"));

        Mockito.verify(servicio, Mockito.times(1)).registrarPersona(personaForm.toPersona(), personaForm.getNombreUsuario(), personaForm.getClave());
    }

    /**
     * Prueba registrar persona cuando el formulario de registro es inválido
     *
     * @throws Exception
     */
    @Test
    public void testRegistrar_personaInvalida_metodo() throws Exception {
        PersonaForm personaForm = new PersonaForm(persona);
        personaForm.setClave2("claveInválida");

        mvc.perform(post("/persona/registrar")
                .sessionAttr("personaForm", personaForm))
                .andExpect(status().isOk())
                .andExpect(view().name("persona/registrar"))
                .andExpect(model().attributeDoesNotExist("usuarioRegistrado"))
                .andExpect(model().hasErrors());

        Mockito.verify(servicio, Mockito.never()).registrarPersona(personaForm.toPersona(), personaForm.getNombreUsuario(), personaForm.getClave());
    }

    /**
     * Prueba registrar persona cuando el nombre de usuario recibido ya se
     * encuentra en uso en el sistema
     *
     * @throws Exception
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testRegistrar_nombreUsuarioOcupado_personaNoGuardadaEInformeAlUsuario() throws Exception {
        PersonaForm personaForm = new PersonaForm(persona);

        Mockito.doThrow(NombreUsuarioOcupadoException.class).when(servicio).registrarPersona(Mockito.isA(Persona.class), Mockito.isA(String.class), Mockito.isA(String.class));

        mvc.perform(post("/persona/registrar")
                .sessionAttr("personaForm", personaForm))
                .andExpect(status().isOk())
                .andExpect(view().name("persona/registrar"))
                .andExpect(model().attributeDoesNotExist("usuarioRegistrado"))
                .andExpect(model().hasErrors());

        verify(servicio, Mockito.times(1)).registrarPersona(personaForm.toPersona(), personaForm.getNombreUsuario(), personaForm.getClave());
    }

    /**
     * Prueba el método que prepara la edición de un registro persona recibiendo
     * el id de la misma
     *
     * @throws Exception
     */
    @Test
    public void testPrepararEditar_idPersonaEnviado_mostrarPaginaDeEdicion() throws Exception {
        mvc.perform(get("/persona/editar/" + persona.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("persona/editar"))
                .andExpect(model().attributeExists("personaForm"));

        verify(servicio, times(1)).getPersona(persona.getId());
    }

    /**
     * Prueba preparar edición de persona cuando el usuario logueado es anónimo.
     * Se espera sea arrojada una excepción de no admitido.
     *
     * @throws Exception
     */
    @Test(expected = NestedServletException.class)
    public void testPrepararEditar_usuarioAnomino_accesoDenegado() throws Exception {
        AnonymousAuthenticationToken token = new AnonymousAuthenticationToken("invitado", persona.getUsuario().getNombre(), new ArrayList<>(persona.getUsuario().getAuthorities()
        ));
        SecurityContextHolder.getContext().setAuthentication(token);

        mvc.perform(get("/persona/editar/" + persona.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("persona/editar"))
                .andExpect(model().attributeExists("personaForm"));
    }

    /**
     * Prueba el método que prepara la edición de un registro persona sin
     * parámetro alguno. Se espera que el sistema realize una búsqueda del id de
     * la persona, para redireccionar al método de petición de edición normal
     *
     * @throws Exception
     */
    @Test
    public void testPrepararEditar_noParametroEnviado_redireccionAEditarNormal() throws Exception {
        TestingAuthenticationToken token = new TestingAuthenticationToken(persona.getUsuario(), persona.getUsuario().getAuthorities());

        mvc.perform(get("/persona/editar").principal(token))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:editar/" + persona.getId()));

        verify(servicio, times(1)).getPersonaId(persona.getUsuario().getNombre());
    }

    /**
     * Test editar persona cuando el formulario tiene datos correctos. Se espera
     * una llamada al método de servicio para editar, así como la variable
     * personaEditada agregada al modelo, para que sea usada como notificación
     * al usuario
     *
     * @throws Exception
     */
    @Test
    public void testEditar_formularioValido_actualizacionRealizada() throws Exception {
        PersonaForm personaForm = new PersonaForm(persona);

        mvc.perform(post("/persona/editar/" + persona.getId())
                .sessionAttr("personaForm", personaForm))
                .andExpect(status().isOk())
                .andExpect(view().name("persona/editar"))
                .andExpect(model().attributeExists("personaForm", "personaEditada"));

        verify(servicio, times(1)).editarPersona(persona);
    }

    /**
     * Test editar persona cuando hay campos inválidos en el formulario
     * recibido. Se espera que no haya llamada alguna para editar la persona en
     * el servicio.
     *
     * @throws Exception
     */
    @Test
    public void testEditar_formularioInvalido_noActualizacionRealizada() throws Exception {
        persona.setNombre("");
        PersonaForm personaForm = new PersonaForm(persona);

        mvc.perform(post("/persona/editar/" + persona.getId())
                .sessionAttr("personaForm", personaForm))
                .andExpect(status().isOk())
                .andExpect(view().name("persona/editar"))
                .andExpect(model().hasErrors())
                .andExpect(model().attributeExists("personaForm"))
                .andExpect(model().attributeDoesNotExist("personaEditada"));

        verify(servicio, never()).editarPersona(persona);
    }

    /**
     * Test editar persona cuando la persona enviada no existe en la base de
     * datos. Se espera que el sistema no falle y una variable llamada
     * registroInexistente sea añadida al modelo
     *
     * @throws Exception
     */
    @Test
    public void testEditar_personaInexistente_variableRegistroNoEncontradoAgregada() throws Exception {
        PersonaForm personaForm = new PersonaForm(persona);
        Mockito.doThrow(RegistroNoEncontradoException.class).when(servicio).editarPersona(persona);

        mvc.perform(post("/persona/editar/" + persona.getId())
                .sessionAttr("personaForm", personaForm))
                .andExpect(status().isOk())
                .andExpect(view().name("persona/editar"))
                .andExpect(model().attributeExists("registroNoEncontrado"))
                .andExpect(model().attributeDoesNotExist("personaEditada"));

    }

    /**
     * Test prepararBorrar. Verifica la vista regresada así como el atributo
     * persona en el modelo
     *
     * @throws Exception
     */
    @Test
    public void prepararBorrar_personaExistente_paginaBorrarRegresada() throws Exception {
        mvc.perform(get("/persona/borrar/" + persona.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("persona/borrar"))
                .andExpect(model().attributeExists("persona"));
    }

    /**
     * Test método borrar. Verifica que la llamada a borrar el registro se haya
     * realizado correctament, así como la vista regresada
     *
     * @throws Exception
     */
    @Test
    public void testBorrar_personaExistente_registroBorrado() throws Exception {
        mvc.perform(post("/persona/borrar/" + persona.getId())
                .sessionAttr("persona", persona))
                .andExpect(status().isOk())
                .andExpect(view().name("persona/borrar"))
                .andExpect(model().attributeExists("personaBorrada"));

        verify(servicio, times(1)).borrarPersona(persona.getId());
    }

    /**
     * Test método borrar cuando no existe una persona con el id proporcionado.
     * Verifica que una variable con nombre registroNoEncontrado se haya
     * agregado al model
     *
     * @throws Exception
     */
    @Test
    public void testBorrar_personaInexistente_varialbeRegistroNoEncontradoAgregada() throws Exception {
        Mockito.doThrow(RegistroNoEncontradoException.class).when(servicio).borrarPersona(persona.getId());

        mvc.perform(post("/persona/borrar/" + persona.getId())
                .sessionAttr("persona", persona))
                .andExpect(status().isOk())
                .andExpect(view().name("persona/borrar"))
                .andExpect(model().attributeExists("registroNoEncontrado"))
                .andExpect(model().attributeDoesNotExist("personaBorrada"));
    }

    /**
     * Crea un view resolver para ser usado en el mockMvc
     *
     * @return
     */
    private ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setContentType("text/html; charset=utf-8");
        return viewResolver;
    }

}

package com.jhovanni.registropersonas.control;

import com.jhovanni.registropersona.excepcion.NombreUsuarioOcupadoException;
import com.jhovanni.registropersona.excepcion.RegistroNoEncontradoException;
import com.jhovanni.registropersonas.entidad.Ciudad;
import com.jhovanni.registropersonas.entidad.Foto;
import com.jhovanni.registropersonas.entidad.Persona;
import com.jhovanni.registropersonas.hibernate.ServicioRegistro;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Jhovanni
 */
@Controller
@SessionAttributes({"persona", "personaForm"})
public class ControlPrincipal {

    private static final Logger log = LogManager.getLogger();
    private List<Ciudad> ciudades;

    @Autowired
    private ServicioRegistro servicio;

    /**
     * Comprueba si un nombre de usuario recibido ya se encuentra en uso en el
     * sistema. El nombre de usuario se comprueba conviertiéndolo a minúsculas
     *
     * @param nombreUsuario
     * @return
     */
    @RequestMapping(value = "persona/isNombreUsuarioOcupado/{nombreUsuario}")
    @ResponseBody
    public boolean isNombreUsuarioOcupado(@PathVariable String nombreUsuario) {
        log.entry(nombreUsuario);
        return log.exit(servicio.isNombreUsuarioOcupado(nombreUsuario.toLowerCase()));
    }

    /**
     * Request mapping para mostrar página de inicio.
     *
     * @return
     */
    @RequestMapping("")
    public String inicio() {
        log.entry();
        return log.exit("inicio");
    }

    /**
     * Agrega una imagen default a la respuesta de la petición.
     *
     * @param response
     */
    @RequestMapping(value = "persona/foto")
    public void mostrarDefaultFoto(HttpServletResponse response) {
        log.entry();
        //TODO: implementar
        log.exit();
    }

    /**
     * Agrega la foto con id {@code id} a la respuesta de la petición
     *
     * @param id
     * @param response
     */
    @RequestMapping(value = "persona/foto/{id}")
    public void mostrarFoto(@PathVariable(value = "id") int id, HttpServletResponse response) {
        log.entry(id);
        Foto foto = servicio.getFoto(id);
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        response.setContentLength(foto.getContenido().length);
        response.setHeader("Content-Disposition", "inline; filename=\"" + foto.getNombre()
                + "\"");
        try {
            response.getOutputStream().write(foto.getContenido());
            response.getOutputStream().flush();
        } catch (IOException | NullPointerException ex) {
            log.error(ex);
        }
        log.exit();
    }

    /**
     * Regresa la lista de ciudades encontradas en el sistema. La lista es
     * actualizada únicamente a cada reinicio del sistema
     *
     * @return
     */
    @ModelAttribute(value = "ciudades")
    public List<Ciudad> ciudades() {
        if (ciudades == null) {
            ciudades = servicio.getCiudades();
        }
        return ciudades;
    }

    /**
     * Proporciona un atributo personaForm, en caso de que la sesión se haya
     * perdido. De otra manera, una HttpSessionRequiredException sería lanzada,
     * y aunque se puede capturar con un ExceptionHandler, aún no encuentro la
     * alternativa más adecuada.<br>
     * El problema con el ExceptionHandler, es que luego de regresar del login
     * (pidiendo la sesión de nuevo), ya no se redirige bien a la página en que
     * estaba el usuario. Y cuando se trate de llamdas AJAX, el problema podría
     * ser peor. Por lo pronto ésta es una solución satisfactoria y que permite
     * seguir realizando los test sin inconvenientes<br>
     * TODO:investigar alternativas a esto
     *
     * @return
     */
    @ModelAttribute
    public PersonaForm personaForm() {
        return new PersonaForm();
    }

    /**
     * Misma funcionalidad que {@link #personaForm() } pero para instancia
     * {@link Persona}
     *
     * @return
     */
    @ModelAttribute
    public Persona persona() {
        return new Persona();
    }

    /**
     * Request mapping para mostrar la página de listar personas. Agrega al
     * mismo tiempo información de las personas contenidas en el sistema
     *
     * @return
     */
    @RequestMapping(value = "persona/lista")
    public ModelAndView listarPersonas() {
        log.entry();
        ModelAndView mv = new ModelAndView("persona/lista");
        mv.addObject("personas", servicio.getPersonas());
        return log.exit(mv);
    }

    /**
     * Muestra la página para registrar una persona. No necesita agregar un
     * objeto PersonaForm al modelo, ya que tal attributo ya se encuentra en el
     * model usando @ModelAttributte
     *
     * @return
     */
    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "persona/registrar", method = RequestMethod.GET)
    public ModelAndView prepararRegistrar() {
        log.entry();
        ModelAndView mv = new ModelAndView("persona/registrar");
        return log.exit(mv);
    }

    /**
     * Procesa la petición para guardar una nueva persona. Si el formulario
     * recibido no contiene errores de validación, o el nombre de usuario no se
     * encuentra ya en uso, la persona es registrada en el sistema. En caso
     * contrario, se regresa los mismos datos contenidos en el formulario,
     * informando al usuario de los campos que debe corregir
     *
     * @param personaForm
     * @param errors
     * @return
     */
    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "persona/registrar", method = RequestMethod.POST)
    public ModelAndView registrar(@Valid @ModelAttribute PersonaForm personaForm, Errors errors) {
        log.entry(personaForm);
        ModelAndView mv = new ModelAndView("persona/registrar");

        if (!Objects.equals(personaForm.getClave(), personaForm.getClave2())) {
            log.debug("Contraseñas recibidas diferentes");
            errors.rejectValue("clave2", "ClaveDiferente");
        }
        if (!errors.hasErrors()) {
            try {
                servicio.registrarPersona(personaForm.toPersona(), personaForm.getNombreUsuario(), personaForm.getClave());
                mv.clear();
                mv.addObject("usuarioRegistrado", true);
                mv.addObject("personaForm", new PersonaForm());
            } catch (NombreUsuarioOcupadoException e) {
                log.info("Nombre de usuario <" + personaForm.getNombreUsuario() + "> ocupado");
                errors.rejectValue("nombreUsuario", "IdOcupado");
            }
        } else {
            log.debug(errors);
        }
        return log.exit(mv);
    }

    /**
     * Muestra la página de una persona según su id
     *
     * @param id
     * @return
     */
    @PreAuthorize(value = "hasAuthority('Administrador')")
    @RequestMapping(value = "persona/editar/{id}", method = RequestMethod.GET)
    public ModelAndView prepararEditar(@PathVariable int id) {
        log.entry(id);
        ModelAndView mv = new ModelAndView("persona/editar");
        Persona persona = servicio.getPersona(id);
        if (persona != null) {
            mv.addObject(persona);
        }
        return log.exit(mv);
    }

    /**
     * Muestra la página de edición con la información del usuario actual
     *
     * @param principal
     * @return
     */
    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "persona/editar", method = RequestMethod.GET)
    public ModelAndView prepararEditar(Principal principal) {
        log.entry(principal);
        ModelAndView mv = new ModelAndView("persona/editar");
        Persona persona = servicio.getPersona(principal.getName());
        if (persona != null) {
            mv.addObject(persona);
        }
        return log.exit(mv);
    }

    /**
     * Procesa la petición de editar la información de una persona. Si los datos
     * recibidos son válidos, la persona es actualizada, en caso contrario se
     * regresan los mismos para que el usuario solucione tales datos incorrectos
     *
     * @param persona
     * @param errors
     * @return
     */
    @PreAuthorize(value = "hasAuthority('Administrador')")
    @RequestMapping(value = "persona/editar/{id}", method = RequestMethod.POST)
    public ModelAndView editar(@Valid Persona persona, Errors errors) {
        log.entry(persona);
        ModelAndView mv = new ModelAndView("persona/editar");
        if (!errors.hasErrors()) {
            try {
                servicio.editarPersona(persona);
                mv.addObject("personaEditada", true);
            } catch (RegistroNoEncontradoException e) {
                log.warn("No se encontró registro alguno que concuerde con el recibido para edición " + persona);
                mv.addObject("registroNoEncontrado", true);
            }
        } else {
            log.debug(errors);
        }
        return log.exit(mv);
    }

    /**
     * Muestra la página borrar para la persona correspondiente al id recibido.
     *
     * @param id
     * @return
     */
    @PreAuthorize(value = "hasAuthority('Administrador')")
    @RequestMapping(value = "persona/borrar/{id}", method = RequestMethod.GET)
    public ModelAndView prepararBorrar(@PathVariable int id) {
        log.entry(id);
        ModelAndView mv = new ModelAndView("persona/borrar");
        Persona persona = servicio.getPersona(id);
        if (persona != null) {
            mv.addObject(persona);
        }
        return log.exit(mv);
    }

    /**
     * Procesa la petición de borrar una persona. Si no existe persona cuyo id
     * corresponda con el recibido, una variable llamada registroNoEncontrado es
     * agregada al modelo
     *
     * @param id
     * @return
     */
    @PreAuthorize(value = "hasAuthority('Administrador')")
    @RequestMapping(value = "persona/borrar/{id}", method = RequestMethod.POST)
    public ModelAndView borrar(@PathVariable int id) {
        log.entry(id);
        ModelAndView mv = new ModelAndView("persona/borrar");
        try {
            servicio.borrarPersona(id);
            mv.addObject("personaBorrada", true);
        } catch (RegistroNoEncontradoException e) {
            log.warn("No se encontró registro alguno que concuerde con el recibido para borrar " + id);
            mv.addObject("registroNoEncontrado", true);
        }
        return log.exit(mv);
    }

    //CONTROLES DE SPRING SECURITY
    /**
     * Muestra la página de login
     *
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String prepararLogin() {
        log.entry();
        return log.exit("login");
    }
}

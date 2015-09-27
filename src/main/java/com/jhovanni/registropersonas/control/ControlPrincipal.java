package com.jhovanni.registropersonas.control;

import com.jhovanni.registropersonas.entidad.Ciudad;
import com.jhovanni.registropersonas.entidad.Foto;
import com.jhovanni.registropersonas.entidad.Persona;
import com.jhovanni.registropersonas.hibernate.Servicio;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Jhovanni
 */
@Controller
public class ControlPrincipal {
    private static final Logger log = LogManager.getLogger();
    private List<Ciudad>ciudades;
    
    @Autowired
    private Servicio servicio;
    
    @RequestMapping("")
    public String inicio() {
        log.entry();
        return log.exit("inicio");
    }

    @RequestMapping(value = "persona/foto")
    public void mostrarDefaultFoto(HttpServletResponse response) {
        log.entry();
        //TODO: implementar
        log.exit();
    }
    
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

    @ModelAttribute(value = "ciudades")
    public List<Ciudad> ciudades() {
        if(ciudades==null){
            ciudades=servicio.getCiudades();
        }
        return ciudades;
    }
    
    @RequestMapping(value = "persona/lista")
    public ModelAndView listarPersonas() {
        log.entry();
        ModelAndView mv = new ModelAndView("persona/lista");
        mv.addObject("personas", servicio.getPersonas());
        return log.exit(mv);
    }

    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "persona/registrar", method = RequestMethod.GET)
    public ModelAndView prepararRegistrar() {
        log.entry();
        ModelAndView mv = new ModelAndView("persona/registrar");
        mv.addObject(new PersonaForm());
        return log.exit(mv);
    }

    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "persona/registrar", method = RequestMethod.POST)
    public ModelAndView registrar(@Valid @ModelAttribute PersonaForm personaForm, Errors errors) {
        ModelAndView mv = new ModelAndView("persona/registrar");
        log.entry(personaForm);

        if (!personaForm.getClave().equals(personaForm.getClave2())) {
            log.debug("contraseñas recibidas diferentes");
            errors.rejectValue("clave2", "ClaveDiferente");
        }
        if (!errors.hasErrors()) {
            try {
                servicio.registrarPersona(personaForm.toPersona(), personaForm.getNombreUsuario(), personaForm.getClave());
                mv.clear();
                mv.addObject(new PersonaForm());
                mv.addObject("usuarioRegistrado", true);
            } catch (DataIntegrityViolationException e) {
                log.debug("nombre de usuario ocupado");
                errors.rejectValue("nombreUsuario", "IdOcupado");
            }catch(Exception e){
                log.error("Hubo un problema al ejecutar la acción " + e);
                errors.reject("Error.desconocido");
            }
        } else {
            log.warn(errors);
        }
        return log.exit(mv);
    }
    @PreAuthorize(value = "hasAuthority('admin')")
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

    @PreAuthorize(value = "hasAuthority('admin')")
    @RequestMapping(value = "persona/editar/{id}", method = RequestMethod.POST)
    public ModelAndView editar(@Valid Persona persona, Errors errors) {
        ModelAndView mv = new ModelAndView("persona/editar");
        log.entry(persona, persona.getUsuario());
        if (!errors.hasErrors()) {
            try {
                servicio.editarPersona(persona);
                mv.addObject("personaEditada", true);
            } catch (DataIntegrityViolationException e) {
                log.debug("nombre de usuario ocupadoo");
                errors.rejectValue("nombreUsuario", "IdOcupado");
            }
        } else {
            log.error(errors);
        }
        return log.exit(mv);
    }

    @PreAuthorize(value = "hasAuthority('admin')")
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

    @PreAuthorize(value = "hasAuthority('admin')")
    @RequestMapping(value = "persona/borrar/{id}", method = RequestMethod.POST)
    public ModelAndView borrar(@PathVariable int id) {
        log.entry(id);
        ModelAndView mv = new ModelAndView("persona/borrar");
        try {
            servicio.borrarPersona(id);
            mv.addObject("personaBorrada", true);
        } catch (Exception e) {
            log.error(e);
        }
        return log.exit(mv);
    }

    //CONTROLES DE SPRING SECURITY
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String prepararLogin() {
        log.entry();
        return log.exit("login");
    }

    @RequestMapping(value = "denegado")
    public ModelAndView accesoDenegado(Principal principal) {
        log.entry(principal);
        ModelAndView mv = new ModelAndView("denegado");
        mv.addObject("nombreUsuario", principal.getName());
        return log.exit(mv);
    }
}

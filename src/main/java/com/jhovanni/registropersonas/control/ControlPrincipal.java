package com.jhovanni.registropersonas.control;

import com.jhovanni.registropersonas.entidad.Ciudad;
import com.jhovanni.registropersonas.entidad.Persona;
import com.jhovanni.registropersonas.hibernate.Servicio;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

    @Autowired
    private Servicio servicio;

    @RequestMapping("")
    public String inicio() {
        return "redirect:persona/lista";
    }

    @RequestMapping(value = "persona/{id}/foto")
    public void mostrarFoto(@PathVariable int id, HttpServletResponse response) throws IOException {
        Persona persona = servicio.getPersona(id);
        byte[] foto = persona.getFoto();
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        response.getOutputStream().write(foto);
        response.getOutputStream().flush();
    }

    @ModelAttribute(value = "ciudades")
    public List<Ciudad> ciudades() {
        return servicio.getCiudades();
    }

    @RequestMapping(value = "persona/lista")
    public ModelAndView listarPersonas() {
        ModelAndView mv = new ModelAndView("persona/lista");
        mv.addObject("personas", servicio.getPersonas());
        return mv;
    }

    @RequestMapping(value = "persona/registrar", method = RequestMethod.GET)
    public ModelAndView prepararRegistrar() {
        ModelAndView mv = new ModelAndView("persona/registrar");
        mv.addObject(new PersonaForm());
        return mv;
    }

    @RequestMapping(value = "persona/registrar", method = RequestMethod.POST)
    public ModelAndView registrar(@Valid PersonaForm personaForm, Errors errors) {
        ModelAndView mv = new ModelAndView("persona/registrar");

        if (!personaForm.getClave().equals(personaForm.getClave2())) {
            errors.rejectValue("clave2", "ClaveDiferente");
        }
        if (!errors.hasErrors()) {
            try {
                servicio.registrarPersona(personaForm.toPersona(), personaForm.getNombreUsuario(), personaForm.getClave());
                mv.clear();
                mv.addObject(new PersonaForm());
                mv.addObject("usuarioRegistrado", true);
            } catch (DataIntegrityViolationException e) {
                errors.rejectValue("nombreUsuario", "IdOcupado");
            }
        } else {
            System.out.println("Errors controlPrincipal.registrar: " + errors);
        }
        return mv;
    }

    @RequestMapping(value = "persona/editar/{id}", method = RequestMethod.GET)
    public ModelAndView prepararEditar(@PathVariable int id) {
        ModelAndView mv = new ModelAndView("persona/editar");
        Persona persona = servicio.getPersona(id);
        if (persona != null) {
            mv.addObject(persona);
        }
        return mv;
    }

    @RequestMapping(value = "persona/editar/{id}", method = RequestMethod.POST)
    public ModelAndView editar(@Valid Persona persona, Errors errors) {
        ModelAndView mv = new ModelAndView("persona/editar");
        System.out.println(persona + " usuario:" + persona.getUsuario());
        if (!errors.hasErrors()) {
            try {
                servicio.editarPersona(persona);
                mv.addObject("personaEditada", true);
            } catch (DataIntegrityViolationException e) {
                errors.rejectValue("nombreUsuario", "IdOcupado");
            }
        } else {
            System.out.println("Errors controlPrincipal.editar: " + errors);
        }
        return mv;
    }

    @RequestMapping(value = "persona/borrar/{id}", method = RequestMethod.GET)
    public ModelAndView prepararBorrar(@PathVariable int id) {
        ModelAndView mv = new ModelAndView("persona/borrar");
        Persona persona = servicio.getPersona(id);
        if (persona != null) {
            mv.addObject(persona);
        }
        return mv;
    }

    @RequestMapping(value = "persona/borrar/{id}", method = RequestMethod.POST)
    public ModelAndView borrar(@PathVariable int id) {
        ModelAndView mv = new ModelAndView("persona/borrar");
        try {
            servicio.borrarPersona(id);
            mv.addObject("personaBorrada", true);
        } catch (Exception e) {
            System.out.println("Excepción ControlPrincipal.borrar:" + e);
        }
        return mv;
    }

    //CONTROLES DE SPRING SECURITY
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String prepararLogin() {
        return "login";
    }

    @RequestMapping(value = "denegado")
    public ModelAndView accesoDenegado(Principal principal) {
        ModelAndView mv = new ModelAndView("denegado");
        mv.addObject("nombreUsuario", principal.getName());
        return mv;
    }
}

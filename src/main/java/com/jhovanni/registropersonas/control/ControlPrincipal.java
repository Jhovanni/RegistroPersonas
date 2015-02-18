package com.jhovanni.registropersonas.control;

import com.jhovanni.registropersonas.entidad.Ciudad;
import com.jhovanni.registropersonas.entidad.Persona;
import com.jhovanni.registropersonas.hibernate.Servicio;
import java.util.List;
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
}
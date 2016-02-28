/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhovanni.registropersonas.control;

import com.jhovanni.registropersonas.hibernate.ServicioRegistro;
import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * Componente utilizar para metodos de petición no encontrador en otros
 * controladores. Usualmente para eception handlers y modelAttributes comunes
 * entre varios controladores (o todos como es el caso de este)
 *
 * @author Administrator
 */
@ControllerAdvice
public class AdvisorController {

    private static final Logger log = LogManager.getLogger();
    @Autowired
    private ServicioRegistro servicioRegistro;

    /**
     * Muestra página a mostrar por defecto cuando la URL solicitada no se
     * encuentre
     *
     * @param request
     * @return
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public String pagina404(HttpServletRequest request) {
        log.entry(request.getServletPath());
        return log.exit("404");
    }

    /**
     * Muestra página de acceso denegado
     *
     * @param principal
     * @return
     */
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public ModelAndView accesoDenegado(Principal principal) {
        log.entry(principal);
        ModelAndView mv = new ModelAndView("denegado");
        String nombreUsuario = servicioRegistro.getPersonaNombre(principal.getName());
        if (nombreUsuario == null) {
            nombreUsuario = principal.getName();
        }
        mv.addObject("nombreUsuario", nombreUsuario);
        return log.exit(mv);
    }

}

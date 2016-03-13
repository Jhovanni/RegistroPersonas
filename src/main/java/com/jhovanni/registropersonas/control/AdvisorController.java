/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhovanni.registropersonas.control;

import com.jhovanni.registropersonas.hibernate.ServicioRegistro;
import java.security.Principal;
import java.util.Date;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.transaction.CannotCreateTransactionException;
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

    /**
     * Handler intermediario para problemas al conectar a la base de datos desde
     * el inicio de la aplicación. <br>Redirecciona al inicio, pero agrega una
     * variable informando que la conexión no está disponible
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    @ExceptionHandler({JpaSystemException.class})
    public ModelAndView problemaConexion(Exception e) {
        log.entry();
        ModelAndView mv = new ModelAndView("inicio");
        mv.addObject("errorConexionClase", e.getClass());
        mv.addObject("errorConexionMensaje", e.getMessage());
        return log.exit(mv);
    }

    /**
     * Proporciona página personalizada para cuando hay errores debido a
     * problemas de conectividad con la base de datos.
     * <br>
     * TODO: quizá reiniciar la conexión ayude, de la misma manera que se usa en
     * sessionFilter, investigar como se hace eso
     *
     * @param request
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.GATEWAY_TIMEOUT)
    @ExceptionHandler({CannotCreateTransactionException.class, DataAccessResourceFailureException.class})
    public ModelAndView pagina504(ServletRequest request, Exception e) {
        log.entry(e);
        ModelAndView mv = new ModelAndView("504");
        mv.addObject("excepcionTimeStamp", new Date());
        mv.addObject("excepcionClase", e.getClass());
        mv.addObject("excepcionMensaje", e.getMessage());

        if (request instanceof HttpServletRequest) {
            HttpServletRequest hsr = (HttpServletRequest) request;
            mv.addObject("metodo", hsr.getMethod());
            mv.addObject("uri", hsr.getRequestURI());
            if (hsr.getUserPrincipal() != null) {
                mv.addObject("nombreUsuario", hsr.getUserPrincipal().getName());
            }
        }
        log.error("Al parecer hay problemas con la base de datos por " + e.getClass() + ":" + e.getMessage());
        return log.exit(mv);
    }

    /**
     * Proporciona página personalizada para cuando existan errores en la
     * aplicación. La excepción será agregada al log con prioridad FATAL
     *
     * @param request
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ModelAndView pagina500(ServletRequest request, Exception e) {
        log.entry(e);
        ModelAndView mv = new ModelAndView("500");
        mv.addObject("excepcionTimeStamp", new Date());
        mv.addObject("excepcionClase", e.getClass());
        mv.addObject("excepcionMensaje", e.getMessage());

        if (request instanceof HttpServletRequest) {
            HttpServletRequest hsr = (HttpServletRequest) request;
            mv.addObject("metodo", hsr.getMethod());
            mv.addObject("uri", hsr.getRequestURI());
            if (hsr.getUserPrincipal() != null) {
                mv.addObject("nombreUsuario", hsr.getUserPrincipal().getName());
            }
        }
        log.fatal("Hora de resolver defectos", e);
        return log.exit(mv);
    }

}

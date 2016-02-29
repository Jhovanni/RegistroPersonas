/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhovanni.registropersonas.config;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * Listener usado para definir el tiempo de sesión antes que ésta expire. La
 * única funcionalidad por el momento, es definir el tiempo de duración de la
 * sesión en el método
 * {@link #sessionCreated(javax.servlet.http.HttpSessionEvent)}<br>La duración
 * de la sesión está definida por la constante interna {@link #TIME_OUT} }
 *
 * @author Administrator
 */
@WebListener
public class SessionTimeOutListener implements HttpSessionListener {

    private final static int TIME_OUT = 5 * 60;

    private static final Logger log = LogManager.getLogger();

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        log.trace("Sesión iniciada");
//        se.getSession().setMaxInactiveInterval(TIME_OUT);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        log.trace("Sesión finalizada. Timeout " + se.getSession().getMaxInactiveInterval());
    }

}

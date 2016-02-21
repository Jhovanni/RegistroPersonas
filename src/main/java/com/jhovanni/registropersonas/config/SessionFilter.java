/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhovanni.registropersonas.config;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Filtro adicional a toda la aplicación, pensado para visualizar mejor las
 * peticiones realizadas. Por el momento no realiza más funcionalidad que
 * delegar el flujo de la petición de manera normal. El objetivo de crearla es
 * para probar el uso de {@linkplain WebFilter} y el uso de filtros mediante
 * JavaConfig
 *
 * @author Administrator
 */
@WebFilter(asyncSupported = true, urlPatterns = "/*", filterName = "sessionFilter")
public class SessionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

}

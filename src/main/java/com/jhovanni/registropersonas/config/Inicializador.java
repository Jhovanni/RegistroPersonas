package com.jhovanni.registropersonas.config;

/**
 * Clase llamada por el cargador de Spring al inicio de este.
 *
 * @author Jhovanni
 */
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class Inicializador implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // Crear el contexto base de Spring
        AnnotationConfigWebApplicationContext rootContext
                = new AnnotationConfigWebApplicationContext();
        rootContext.register(RootConfig.class);

        // Manejar el ciclo de vida del contexto base
        servletContext.addListener(new ContextLoaderListener(rootContext));

        // Crear el dispatcher servlet de Spring usando contexto de aplicación
        AnnotationConfigWebApplicationContext dispatcherContext
                = new AnnotationConfigWebApplicationContext();
        dispatcherContext.register(DispatcherConfig.class);

        // Registrar y mapear el dispatcher al servlet
        ServletRegistration.Dynamic dispatcher
                = servletContext.addServlet("dispatcher", new DispatcherServlet(dispatcherContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }
}

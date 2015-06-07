package com.jhovanni.registropersonas.hibernate;

import com.jhovanni.registropersonas.config.RootConfig;
import com.jhovanni.registropersonas.entidad.Usuario;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Jhovanni
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
public class TestHibernate {

    @Autowired
    private Servicio servicio;
    @Autowired
    private SessionFactory sessionFactory;
    
    @Test
    public void testConexion(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createSQLQuery("SELECT 1 FROM DUAL");
    }
    
    @Ignore
    @Test
    public void testGetUsuario(){
        Usuario usuario=servicio.getUsuario("admin");
        assertNotNull(usuario);
        System.out.println(usuario);
    }
}

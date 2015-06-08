package com.jhovanni.registropersonas.hibernate;

import com.jhovanni.registropersonas.config.RootConfig;
import com.jhovanni.registropersonas.entidad.Usuario;
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
 * Test case para probar la conexión a base de datos
 *
 * @author Jhovanni
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
public class TestSessionFactory {

    @Autowired
    private Servicio servicio;
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Test conexión a base de datos Comprueba que la base de datos reponda a la
     * solicitud de conexión correctamente
     */
    @Test
    public void testConexion() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createSQLQuery("SELECT 1 FROM DUAL");
    }
}

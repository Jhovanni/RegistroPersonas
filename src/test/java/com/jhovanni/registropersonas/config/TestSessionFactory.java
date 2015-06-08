package com.jhovanni.registropersonas.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
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
    private static final Logger log = LogManager.getLogger();
    
    @Autowired
    private SessionFactory sessionFactory;
    /**
     * Test conexión a base de datos Comprueba que la base de datos reponda a la
     * solicitud de conexión correctamente
     */
    @Test
    public void testConexion() {
        log.entry();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createSQLQuery("SELECT 1 FROM DUAL");
        log.exit();
    }
}

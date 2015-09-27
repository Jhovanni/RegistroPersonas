package com.jhovanni.registropersonas.repositorio;

import com.jhovanni.registropersonas.config.RootConfigTest;
import com.jhovanni.registropersonas.entidad.Ciudad;
import java.util.List;
import junit.framework.TestCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author jhovanni
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfigTest.class)
//@Ignore
public class CiudadRepositoryTest extends TestCase {

    private final Logger log = LogManager.getLogger();
    @Autowired
    private CiudadRepository ciudadRepository;

    /**
     * Test find all Ciudades usando Spring Data Jpa mediante Hibernate
     */
    @Test
    public void testFindAllCiudad() {
        assertNotNull(this);

        List<Ciudad> ciudades = ciudadRepository.findAll();

        for (Ciudad ciudad : ciudades) {
            log.info(ciudad);
        }
    }
}

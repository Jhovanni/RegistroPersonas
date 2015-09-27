/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhovanni.registropersonas.repositorio;

import com.jhovanni.registropersonas.config.RootConfigTest;
import com.jhovanni.registropersonas.entidad.Persona;
import java.util.List;
import junit.framework.TestCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * PersonaRespository tests.
 *
 * @author Administrator
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfigTest.class)
public class PersonaRepositoryTest extends TestCase {

    private static final Logger log = LogManager.getLogger();
    @Autowired
    private PersonaRepository personaRepository;

    /**
     * Buscar todos los registros Persona
     */
    @Test
    @Rollback(true)
    public void testFindAll() {
        List<Persona> personas = personaRepository.findAll();
        assertNotNull(personas);
        log.info(personas);
    }

    /**
     * Buscar todos los registros Persona, sin información de la fotografía
     * asociada
     */
    @Test
    @Rollback(true)
    public void testFindAllForListing() {
        List<Persona> personas = personaRepository.findAllForListing();
        assertNotNull(personas);
        log.info(personas);
    }

}

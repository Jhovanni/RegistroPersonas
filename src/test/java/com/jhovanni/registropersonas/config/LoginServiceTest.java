package com.jhovanni.registropersonas.config;

import com.jhovanni.registropersonas.hibernate.LoginService;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Test case para LoginService
 *
 * @author jhovanni
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
public class LoginServiceTest {

    @Autowired
    private LoginService loginService;

    /**
     * Test loadUserByUsername Comprueba que el usuario con nombre 'admin' (el
     * primero en ser creado por lo general) es leido correctamente de la base
     * de datos
     */
    @Test
    public void testUserDetails() {
        assertNotNull(loginService.loadUserByUsername("admin"));
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhovanni.registropersonas.config;

import junit.framework.TestCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.junit.Ignore;

/**
 *
 * @author Jhovanni
 */
@Ignore
public class PasswordEncoderTest extends TestCase{
    private static final Logger log = LogManager.getLogger();
    @Test
    public void testEncoder(){
        PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        assertNotNull(passwordEncoder.encode("admin"));
        log.info(passwordEncoder.encode("admin"));
    }
    
}

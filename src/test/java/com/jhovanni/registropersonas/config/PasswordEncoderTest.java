/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhovanni.registropersonas.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Jhovanni
 */
public class PasswordEncoderTest{
    private static final Logger log = LogManager.getLogger();
    @Ignore
    @Test
    public void testEncoder(){
        PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        assertNotNull(passwordEncoder.encode("admin"));
        log.info(passwordEncoder.encode("admin"));
    }
    
}

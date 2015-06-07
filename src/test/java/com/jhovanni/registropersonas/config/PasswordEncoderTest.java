/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhovanni.registropersonas.config;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.junit.Assert.*;

/**
 *
 * @author Jhovanni
 */
public class PasswordEncoderTest{
    @Test
    public void testEncoder(){
        PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        assertNotNull(passwordEncoder.encode("admin"));
        System.out.println(passwordEncoder.encode("admin"));
    }
    
}

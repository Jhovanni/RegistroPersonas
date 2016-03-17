/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhovanni.registropersonas.hibernate;

import com.jhovanni.registropersonas.entidad.Usuario;
import com.jhovanni.registropersonas.factory.UsuarioFactory;
import com.jhovanni.registropersonas.repositorio.UsuarioRepository;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 * @author Administrator
 */
@RunWith(MockitoJUnitRunner.class)
public class LoginServiceTest extends TestCase {

    @InjectMocks
    private ServicioRegistro servicio;

    @InjectMocks
    private LoginService loginService;
    @Mock
    private UsuarioRepository usuarioRepository;

    private String nombreUsuario;

    @Before
    public void before() {
        nombreUsuario = "nombreUsuario";

    }

    /**
     * Prueba cargar un usuario cuando tal usuario no existe. En tal caso se
     * debe arrojar una excepción {@link UsernameNotFoundException} que spring
     * entenderá adecuadamente
     */
    @Test(expected = UsernameNotFoundException.class)
    public void testLoadUserByUsername_usuarioNoEncontrado_excepcionArrojada() {
        loginService.loadUserByUsername(nombreUsuario);
        Mockito.verify(usuarioRepository, Mockito.times(1)).findOne(nombreUsuario);
    }

    /**
     * Prueba cargar un usuario. Para tal acción, el sistema debe llamar buscar
     * el la base de datos el usuario, y regresarlo al invocador.
     */
    @Test
    public void testLoadUserByUsername_usuarioEncontrado_registroDevuelto() {
        Mockito.when(usuarioRepository.findOne(Mockito.isA(String.class))).thenReturn(UsuarioFactory.get());

        assertTrue(loginService.loadUserByUsername(nombreUsuario) instanceof Usuario);
        Mockito.verify(usuarioRepository, Mockito.times(1)).findOne(nombreUsuario);

    }

}

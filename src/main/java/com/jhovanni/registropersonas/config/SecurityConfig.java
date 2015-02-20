package com.jhovanni.registropersonas.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

/**
 *
 * @author Jhovanni
 */
@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("jhovanni").password("jhovanni").roles("usuario", "admin");
        auth.inMemoryAuthentication().withUser("carlos").password("carloscarlos").roles("usuario");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/login", "/logout", "/persona/lista").permitAll()
                .antMatchers("/persona/registrar").hasAnyRole("admin", "usuario")
                .antMatchers("/persona/editar/**", "/persona/borrar/**").hasRole("admin")
                .and().formLogin()
                .usernameParameter("usuario")
                .passwordParameter("clave")
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .failureUrl("/login?error") //la '/' al inicio de la url es importante ,-.
                .and().rememberMe()
                .and().logout()
                .invalidateHttpSession(false)
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .and().exceptionHandling()
                .accessDeniedPage("/denegado");
    }

}

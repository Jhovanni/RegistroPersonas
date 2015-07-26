package com.jhovanni.registropersonas.secure;

import com.jhovanni.registropersonas.config.DispatcherConfig;
import com.jhovanni.registropersonas.config.RootConfig;
import junit.framework.TestCase;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Test 'basic' secure rules using JSON file for test. This basic secure rules
 * refers to those that imply: id, access, edition and roles variables; status,
 * contacts and freeForDST are not tested here
 *
 * @author jhovanni
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class, DispatcherConfig.class})
@WebAppConfiguration
@Ignore
public class SecureManagerTest extends TestCase {

    @Autowired
    private SecureManager manager;
    @Autowired
    private SecureRuleservice service;

    @Test
    public void testDependencies() {
        assertNotNull(manager);
    }

    /**
     * Test the manager throws and SecureRuleNotFoundException when used with an
     * id not defined in the JSON file
     */
    @Test(expected = SecureRuleNotFoundException.class)
    public void testRuleNotDefined() {
        manager.setJSONFileURL("secureRuleTest.json");
        manager.authorize("undefinedRule");

    }

    /**
     * Test rule 'example01' Access to users with at least one role
     */
    @Test
    public void testAuthorizeExample01() {
        manager.setJSONFileURL("secureRuleTest.json");

        //no user logged, he shouln't have access
        assertFalse(manager.authorize("example01"));

        //User logged without roles, he shouln't have access
        User user = new User("tester", "password", AuthorityUtils.NO_AUTHORITIES);
        setAuthentication(user);
        assertFalse(manager.authorize("example01"));

        //User logged with al least one role, he should have access
        user = new User("tester", "password", AuthorityUtils.createAuthorityList("Administrator"));
        setAuthentication(user);
        assertTrue(manager.authorize("example01"));
    }

    /**
     * Test rule 'example02' Access only to users with any role 'Administrator'
     * 'Engagement Lead'
     */
    @Test
    public void testAuthorizeExample02() {
        manager.setJSONFileURL("secureRuleTest.json");

        //User logged with roles but not 'Administrator' or 'Engagement Lead', he shouln't have access
        User user = new User("tester", "password", AuthorityUtils.createAuthorityList("Financial"));
        setAuthentication(user);
        assertFalse(manager.authorize("example02"));

        //User logged with role 'Administrator', he should have access
        user = new User("tester", "password", AuthorityUtils.createAuthorityList("Administrator"));
        setAuthentication(user);
        assertTrue(manager.authorize("example02"));

        //User logged with role 'Engagement Lead', he should have access
        user = new User("tester", "password", AuthorityUtils.createAuthorityList("Engagement Lead"));
        setAuthentication(user);
        assertTrue(manager.authorize("example02"));
    }

    /**
     * Test rule 'example02', establish full access to users with any role
     * 'Administrator' 'Engagement Lead' 'Engagement Lead'
     */
    @Test
    public void testAuthorizeExample03() {
        manager.setJSONFileURL("secureRuleTest.json");

        //User logged with roles but not 'Administrator' or 'Engagement Lead', readonly access
        User user = new User("tester", "password", AuthorityUtils.createAuthorityList("Financial"));
        setAuthentication(user);
        assertEquals(SecureManager.READONLY, manager.authorizeInput("example03"));
        
        //User logged with role 'Administrator', he should have editable access
        user = new User("tester", "password", AuthorityUtils.createAuthorityList("Administrator"));
        setAuthentication(user);
        assertEquals(SecureManager.EDITABLE, manager.authorizeInput("example03"));
        
        //User logged with role 'Engagement Lead', he should have access
        user = new User("tester", "password", AuthorityUtils.createAuthorityList("Engagement Lead"));
        setAuthentication(user);
        assertEquals(SecureManager.EDITABLE, manager.authorizeInput("example03"));
    }

    private TestingAuthenticationToken getAuthentication() {
        User user = new User("tester", "password", AuthorityUtils.createAuthorityList("Admin"));
        return setAuthentication(user);
    }

    private TestingAuthenticationToken setAuthentication(UserDetails user) {
        TestingAuthenticationToken token = new TestingAuthenticationToken(user, null);
        SecurityContextHolder.getContext().setAuthentication(token);
        return token;
    }

}

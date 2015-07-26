package com.jhovanni.registropersonas.secure;

import com.jhovanni.registropersonas.config.DispatcherConfig;
import com.jhovanni.registropersonas.config.RootConfig;
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
import org.springframework.test.context.web.WebAppConfiguration;

/**
 *
 * @author jhovanni
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class, DispatcherConfig.class})
@WebAppConfiguration
@Ignore
public class SecureRuleRepositoryTest extends TestCase{
    private static final Logger log = LogManager.getLogger();
   @Autowired
   private SecureRuleservice repositorio;
   
    @Test
    public void testDependencias() {
        assertNotNull(repositorio);
    }
    
    @Test
    public void testLoadJSON() {
        repositorio.setJSONFileUrl("secureRuleTest.json");
        try {
            repositorio.getJSONInputStream();
        } catch (Exception e) {
            TestCase.fail("JSON file not found");
        }
    }

    @Test
    public void testGetRules() {
        repositorio.setJSONFileUrl("secureRuleTest.json");
        List<SecureRule> rules = repositorio.getRules();
        assertNotNull(rules);
        log.info(rules);
    }

    @Test
    public void testFindById() {
        repositorio.setJSONFileUrl("secureRuleTest.json");
        SecureRule rule = repositorio.findById("example01");
        assertNotNull(rule);
        log.info(rule);
    }
    
}

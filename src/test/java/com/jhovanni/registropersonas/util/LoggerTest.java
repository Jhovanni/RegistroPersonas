package com.jhovanni.registropersonas.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author jhovanni
 */
public class LoggerTest {
    private static final Logger log = LogManager.getLogger();
    @Ignore
    @Test
    public void testLogger(){
        log.entry();
        log.trace("trace");
        log.debug("debug");
        log.info("info");
        log.warn("warn");
        log.error("error");
        log.fatal("fatal");
        log.exit();
    }
}

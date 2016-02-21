package com.jhovanni.registropersonas.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.junit.Test;

/**
 *
 * @author jhovanni
 */
public class LoggerTest {

    private static final Logger log = LogManager.getLogger();

    @Test
    public void testLogger() {
        log.entry();
        log.trace("trace");
        log.debug("debug");
        log.info("info");
        log.warn("warn");
        log.error("error");
        log.fatal("fatal");
        log.exit();

        log.trace("Cambiando nivel de logueo a warn");
        setLogLevel(Level.WARN);

        log.entry();
        log.trace("trace");
        log.debug("debug");
        log.info("info");
        log.warn("warn");
        log.error("error");
        log.fatal("fatal");
        log.exit();
    }

    /**
     * Cambia el nivel de logueo para toda la aplicación dinámicamente
     *
     * @param level
     */
    public static void setLogLevel(Level level) {
        LoggerContext context = (LoggerContext) LogManager.getContext(false);
        Configuration configuration = context.getConfiguration();
        configuration.getLoggerConfig(LogManager.ROOT_LOGGER_NAME).setLevel(level);
        context.updateLoggers(configuration);
    }
}

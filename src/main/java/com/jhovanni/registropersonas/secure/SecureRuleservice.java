package com.jhovanni.registropersonas.secure;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 *
 * @author jhovanni
 */
@Component
public class SecureRuleservice {

    private static final Logger log = LogManager.getLogger();
    private boolean debugMode = false;
    private String jsonFileUrl = "";
    private List<SecureRule> rules;

    public SecureRule findById(String id) throws SecureRuleNotFoundException{
        rules = getRules();
        for (SecureRule rule : rules) {
            if(rule.getId().equals(id)){
                return rule;
            }
        }
        log.fatal("Rule with id='" + id + "' not found");
        throw new SecureRuleNotFoundException(id);
    }

    boolean isDebugMode() {
        return debugMode;
    }

    void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }

    String getJSONFileUrl() {
        return this.jsonFileUrl;
    }

    void setJSONFileUrl(String url) {
        this.jsonFileUrl = url;
    }

    InputStream getJSONInputStream() throws IOException {
        InputStream is = SecureRuleservice.class.getClassLoader().getResourceAsStream(jsonFileUrl);
        if (is == null) {
            log.fatal("JSON file not found in URL '" + jsonFileUrl + "'");
            throw new IOException("JSON file not found in URL '" + jsonFileUrl + "'");
        }
        return is;
    }

    List<SecureRule> getRules() throws RuntimeException {
        if (rules != null && !debugMode) {
            return rules;
        }
        InputStream jsonInputStream;
        try {
            jsonInputStream = getJSONInputStream();
        } catch (IOException ex) {
            log.fatal("JSON file not found in URL '" + jsonFileUrl + "'");
            throw new RuntimeException("JSON file not found in URL '" + jsonFileUrl + "'"+ex);
        }
        ObjectMapper mapper = new ObjectMapper();
        JavaType javaType = mapper.getTypeFactory().constructCollectionType(List.class, SecureRule.class);
        try {
            rules = mapper.readValue(jsonInputStream, javaType);
        } catch (IOException ex) {
            log.fatal("IOException pargins JSON");
            throw new RuntimeException("IOException pargins JSON "+ex);
        }
        return rules;
    }
}

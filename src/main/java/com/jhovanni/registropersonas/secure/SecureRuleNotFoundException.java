package com.jhovanni.registropersonas.secure;

/**
 *
 * @author jhovanni
 */
public class SecureRuleNotFoundException extends RuntimeException {

    public SecureRuleNotFoundException() {
    }

    public SecureRuleNotFoundException(String id) {
        super("Rule with id='" + id + "' not found");
    }

}

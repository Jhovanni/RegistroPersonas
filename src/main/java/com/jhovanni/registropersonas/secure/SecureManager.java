package com.jhovanni.registropersonas.secure;

import java.util.Collection;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * Manager class for secure rules written on the JSON file
 *
 * @author jhovanni
 * @see SecureRule
 */
@Component
public class SecureManager {

    private final Logger log = LogManager.getLogger();
    public static final String READONLY = "readonly";
    public static final String EDITABLE = "visible";
    public static final String HIDDEN = "hidden";
    @Autowired
    private SecureRuleservice ruleRepositorio;

    /**
     * Assesses whether the state of the application allows access according to
     * security rules described in the JSON file
     *
     * @param id unique identifier string to look for rule in the JSON file
     * @return true if the rule says the user has access, false otherwise
     * @throws SecureRuleNotFoundException when the rule is not found in the
     * JSON file
     */
    public boolean authorize(String id) throws SecureRuleNotFoundException {
        log.entry(id);
        SecureRule rule = ruleRepositorio.findById(id);
        
        if (!rule.isAccess()) {
            log.info("Rule specify no access to anyone");
            return log.exit(false);
        }

//        Next validation will be only when received an engagement parametter (overloaded method)
//        if (!validateStatus(rule, engagement)) {
//            log.exit("Engagement is not on a valid status");
//            return log.exit(false);
//        }
//        This validation also will be only when received an engagement parametter (overloaded method)
//        if (validateContacts(user, rule, engagement)) {
//            log.info("User is accepted contact");
//            return log.exit(true);
//        }
        UserDetails user = getUser();
        if (validateRoles(user, rule)) {
            return log.exit(true);
        }

        if (validateFreeForDST(user, rule)) {
            log.info("User is DST, HAS access");
            return true;
        }

        log.info("User DOES NOT meet any of the rule conditions");
        return log.exit(false);
    }

    /**
     * Determines access available to an HTML element, according to the rules
     * specified in the JSON file, this method is intended for use only in
     * front-end
     *
     * @param id unique identifier string to look for rule in the JSON file
     * @return READONLY for only access to view, EDITABLE for access to view and
     * edition, HIDDEN if the HTML F tag should be hidden edit the element
     * @throws SecureRuleNotFoundException when the rule is not found in the
     * JSON file
     */
    public String authorizeInput(String id) throws SecureRuleNotFoundException, RuntimeException {
        log.entry(id);
        SecureRule rule = ruleRepositorio.findById(id);
        
        if (!rule.isAccess()) {
            log.info("Rule specify HIDDEN to all");
            return log.exit(HIDDEN);
        }
        if(!rule.isEdition()){
            log.info("Rule specify READONLY to all");
            return log.exit(READONLY);
        }
        
        UserDetails user = getUser();
        if (validateRoles(user, rule)) {
            return log.exit(EDITABLE);
        }

        if (validateFreeForDST(user, rule)) {
            log.info("User is DST, HAS access");
            return log.exit(EDITABLE);
        }

        log.info("User DOES NOT meet any of the rule conditions");
        return log.exit(READONLY);
    }

    /**
     * Return the state of debug mode. When this is ON, the application reloads
     * the JSON file EVERY time it evaluates an rule
     *
     * @return true if debug mode is on, false otherwise
     * @see setDebugMode(boolean)
     */
    public boolean isDebugMode() {
        return ruleRepositorio.isDebugMode();
    }

    /**
     * Sets debug mode status, by default this mode is off (false) when the
     * application starts. Please be careful with this method and NEVER USED it
     * with parameter true in production, since it will activate debug mode,
     * reducing performance considerably
     *
     * @param debugMode
     * @see isDebugMode()
     */
    public void setDebugMode(boolean debugMode) {
        ruleRepositorio.setDebugMode(debugMode);
    }

    /**
     * Obtains JSON file URL location
     *
     * @return
     */
    public String getJSONFileUrl() {
        return ruleRepositorio.getJSONFileUrl();
    }

    /**
     * Set location for the JSON file. Please note that, once the application
     * has started, this method WILL ONLY has effect if debug mode is on
     *
     * @param url
     * @see setDebugMode(boolean)
     */
    public void setJSONFileURL(String url) {
        ruleRepositorio.setJSONFileUrl(url);
    }

    /**
     * Determines if the user meet any of the rule criteria for roles
     *
     * @param user
     * @param rule
     * @return true if the rule allow access (by explicity declaration or by
     * wildcards) to any of the user roles, false otherwise otherwise
     */
    private boolean validateRoles(UserDetails user, SecureRule rule) {        
        //validate wildcards first
        if (user == null) {
            log.info("No logged user, NO access");
            return false;
        }
        log.debug("Validating user roles " + user.getAuthorities() + ", against rule roles accepted " + rule.getRoles() + "");

        Collection<? extends GrantedAuthority> roles = user.getAuthorities();
        if (rule.getRoles().contains("*")) {
            if (roles.size() > 0) {
                log.info("Wildcard '*': User with roles HAS access");
                return true;
            } else {
                log.info("Wildcard '*': User without roles HAS NOT access");
                return false;
            }
        }

        //validate explicity declarations of roles
        Set<String> ruleRoles = rule.getRoles();
        for (GrantedAuthority role : user.getAuthorities()) {
            if (ruleRoles.contains(role.getAuthority())) {
                log.info("User role '" + role.getAuthority() + "'  HAS access");
                return true;
            }
        }
        
        //user roles desn't math any criteria
        return false;
    }

    /**
     * Evaluates if user matches freeForDST rule criteria
     *
     * @param user
     * @return true if the rule allow access to DST users, and the user is one
     * of them; false otherwise
     */
    private boolean validateFreeForDST(UserDetails user, SecureRule rule) {
        //TODO: implemente this
        return false;
    }

    /**
     * Get the current logged user
     *
     * @return
     */
    private UserDetails getUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        Object principal = null;
        try {
            principal = context.getAuthentication().getPrincipal();
        } catch (NullPointerException e) {
            log.warn("No authentification token found");
        }

        if (principal instanceof UserDetails) {
            return (UserDetails) principal;
        }
        return null;
    }

}

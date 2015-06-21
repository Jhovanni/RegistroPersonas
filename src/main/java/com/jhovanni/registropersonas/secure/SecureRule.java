package com.jhovanni.registropersonas.secure;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author jhovanni
 */
public class SecureRule implements Serializable{
    private String id;
    private boolean access;
    private boolean edition=false;
    private boolean freeForDST=false;
    private Set<String>roles;
    private Set<String>contacts=new HashSet<>();
    private Set<String>status=new HashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isAccess() {
        return access;
    }

    public void setAccess(boolean access) {
        this.access = access;
    }

    public boolean isEdition() {
        return edition;
    }

    public void setEdition(boolean edition) {
        this.edition = edition;
    }

    public boolean isFreeForDST() {
        return freeForDST;
    }

    public void setFreeForDST(boolean freeForDST) {
        this.freeForDST = freeForDST;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public Set<String> getContacts() {
        return contacts;
    }

    public void setContacts(Set<String> contacts) {
        this.contacts = contacts;
    }

    public Set<String> getStatus() {
        return status;
    }

    public void setStatus(Set<String> status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SecureRule other = (SecureRule) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SecureRule{" + "id=" + id + ", access=" + access + ", editon=" + edition + ", freeForDST=" + freeForDST + '}';
    }
    
}

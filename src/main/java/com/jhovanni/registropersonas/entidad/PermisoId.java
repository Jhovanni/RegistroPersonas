package com.jhovanni.registropersonas.entidad;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Jhovanni
 */
public class PermisoId implements Serializable {
    private static final long serialVersionUID = 1L;

    private String usuario;
    private Nivel nivel;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + Objects.hashCode(this.usuario);
        hash = 31 * hash + Objects.hashCode(this.nivel);
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
        final PermisoId other = (PermisoId) obj;
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        return this.nivel == other.nivel;
    }

    @Override
    public String toString() {
        return "PermisoPK{" + "usuario=" + usuario + ", nivel=" + nivel + '}';
    }
    
}

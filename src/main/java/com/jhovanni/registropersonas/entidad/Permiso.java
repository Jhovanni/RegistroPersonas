package com.jhovanni.registropersonas.entidad;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author Jhovanni
 */
@Entity
@Table
@IdClass(PermisoId.class)
public class Permiso implements Serializable, GrantedAuthority {

    @Id
    @JoinColumn(name = "nombre_usuario", referencedColumnName = "nombre")
    @ManyToOne
    private Usuario usuario;
    @Id
    @Enumerated(EnumType.STRING)
    private Nivel nivel;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
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
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.usuario);
        hash = 29 * hash + Objects.hashCode(this.nivel);
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
        final Permiso other = (Permiso) obj;
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        return this.nivel == other.nivel;
    }

    @Override
    public String toString() {
        return "Permiso{" + "nombreUsuario=" + usuario.getNombre() + ", nivel=" + nivel + '}';
    }

    @Override
    public String getAuthority() {
        return this.nivel.toString();
    }
}

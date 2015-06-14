package com.jhovanni.registropersonas.entidad;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Jhovanni
 */
@Entity
@Table(name = "FOTOS")
public class Foto implements Serializable{
    @Id
    @GeneratedValue
    private int id;
    @Size(max = 30)
    private String nombre;
    @Lob
    @NotNull
    private byte[]contenido;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public byte[] getContenido() {
        return contenido;
    }

    public void setContenido(byte[] contenido) {
        this.contenido = contenido;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.id;
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
        final Foto other = (Foto) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "Foto{" + "id=" + id + ", nombre=" + nombre + '}';
    }
}

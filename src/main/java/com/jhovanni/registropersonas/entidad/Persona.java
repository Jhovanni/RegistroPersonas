package com.jhovanni.registropersonas.entidad;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

/**
 *
 * @author Jhovanni
 */
@Entity
@Table(name = "PERSONAS")
public class Persona implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    @NotNull
    @Size(min = 3, max = 20)
    private String nombre;
    @Range(min = 15, max = 70)
    private int edad;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Genero genero;
    @Lob
    private Serializable foto;
    @JoinColumn(name = "NOMBRE_USUARIO", referencedColumnName = "NOMBRE")
    @OneToOne
    private Usuario usuario;
    @JoinColumn(name = "ID_CIUDAD", referencedColumnName = "ID")
    @ManyToOne
    @NotNull
    private Ciudad ciudad;

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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Serializable getFoto() {
        return foto;
    }

    public void setFoto(Serializable foto) {
        this.foto = foto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.id;
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
        final Persona other = (Persona) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "Persona{" + "id=" + id + ", nombre=" + nombre + ", edad=" + edad + ", genero=" + genero + ", foto=" + foto + ", ciudad=" + ciudad + '}';
    }

}
